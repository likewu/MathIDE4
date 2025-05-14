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

package com.blacksquircle.ui.feature.shortcuts.interactor

import com.blacksquircle.ui.feature.shortcuts.data.interactor.ShortcutInteractorImpl
import com.blacksquircle.ui.feature.shortcuts.domain.ShortcutRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ShortcutInteractorTest {

    private val shortcutRepository = mockk<ShortcutRepository>(relaxed = true)
    private val shortcutInteractor = ShortcutInteractorImpl(shortcutRepository)

    @Test
    fun `When load shortcuts Then load from repository`() = runTest {
        // When
        shortcutInteractor.loadShortcuts()

        // Then
        coVerify(exactly = 1) { shortcutRepository.loadShortcuts() }
    }
}