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

package com.blacksquircle.ui.feature.settings.ui.git.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.blacksquircle.ui.ds.PreviewBackground
import com.blacksquircle.ui.ds.dialog.AlertDialog
import com.blacksquircle.ui.ds.textfield.TextField
import com.blacksquircle.ui.feature.settings.R
import com.blacksquircle.ui.ds.R as UiR

@Composable
internal fun GitCredentialsScreen(
    initialUsername: String,
    initialPassword: String,
    onConfirmClicked: (String, String) -> Unit = { _, _ -> },
    onDismissClicked: () -> Unit = {},
) {
    var username by rememberSaveable { mutableStateOf(initialUsername) }
    var password by rememberSaveable { mutableStateOf(initialPassword) }

    AlertDialog(
        title = stringResource(R.string.pref_git_credentials_title),
        content = {
            Column {
                TextField(
                    inputText = username,
                    onInputChanged = { username = it },
                    labelText = stringResource(R.string.pref_git_credentials_username),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                    ),
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    inputText = password,
                    onInputChanged = { password = it },
                    labelText = stringResource(R.string.pref_git_credentials_password),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        keyboardType = KeyboardType.Password,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                )
            }
        },
        confirmButton = stringResource(UiR.string.common_save),
        onConfirmClicked = { onConfirmClicked(username, password) },
        dismissButton = stringResource(android.R.string.cancel),
        onDismissClicked = onDismissClicked,
        onDismiss = onDismissClicked,
    )
}

@PreviewLightDark
@Composable
private fun GitCredentialsScreenPreview() {
    PreviewBackground {
        GitCredentialsScreen(
            initialUsername = "Username",
            initialPassword = "Password"
        )
    }
}