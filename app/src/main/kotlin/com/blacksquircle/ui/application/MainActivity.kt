/*
 * Copyright 2025 Squircle CE contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blacksquircle.ui.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.app.AppOpsManager
import android.app.ComponentCaller
import android.provider.Settings
import android.content.Context
import android.content.Intent

internal class MainActivity : ComponentActivity() {
    companion object {
        private val MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS: Int = 1101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(savedInstanceState)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, 
        data: Intent?, caller: ComponentCaller) {
        if (requestCode == MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS) {
            if (!hasPermission()) {
                startActivityForResult(
                    Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS),
                    MY_PERMISSIONS_REQUEST_PACKAGE_USAGE_STATS);
            }
        }
    }

    private fun hasPermission(): Boolean {
        val appOps: AppOpsManager = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager;
        var mode = 0;
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.KITKAT) {
            mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        }
        return mode == AppOpsManager.MODE_ALLOWED;
    }
}