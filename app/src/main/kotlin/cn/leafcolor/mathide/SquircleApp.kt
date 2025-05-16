package cn.leafcolor.mathide

import android.app.Application
import com.blacksquircle.ui.core.internal.CoreApiDepsProvider
import com.blacksquircle.ui.core.internal.CoreApiProvider
import com.blacksquircle.ui.core.logger.AndroidTree
import com.blacksquircle.ui.feature.editor.api.internal.EditorApiDepsProvider
import com.blacksquircle.ui.feature.editor.api.internal.EditorApiProvider
import com.blacksquircle.ui.feature.explorer.api.internal.ExplorerApiDepsProvider
import com.blacksquircle.ui.feature.explorer.api.internal.ExplorerApiProvider
import com.blacksquircle.ui.feature.fonts.api.internal.FontsApiDepsProvider
import com.blacksquircle.ui.feature.fonts.api.internal.FontsApiProvider
import com.blacksquircle.ui.feature.git.api.internal.GitApiDepsProvider
import com.blacksquircle.ui.feature.git.api.internal.GitApiProvider
import com.blacksquircle.ui.feature.servers.api.internal.ServersApiDepsProvider
import com.blacksquircle.ui.feature.servers.api.internal.ServersApiProvider
import com.blacksquircle.ui.feature.shortcuts.api.internal.ShortcutsApiDepsProvider
import com.blacksquircle.ui.feature.shortcuts.api.internal.ShortcutsApiProvider
import com.blacksquircle.ui.feature.themes.api.internal.ThemesApiDepsProvider
import com.blacksquircle.ui.feature.themes.api.internal.ThemesApiProvider
import com.blacksquircle.ui.internal.di.AppComponent
import timber.log.Timber

internal class SquircleApp : Application(),
    CoreApiProvider,
    EditorApiProvider,
    ExplorerApiProvider,
    FontsApiProvider,
    GitApiProvider,
    ServersApiProvider,
    ShortcutsApiProvider,
    ThemesApiProvider {

    private val appComponent: AppComponent
        get() = AppComponent.buildOrGet(this)

    override fun onCreate() {
        super.onCreate()
        AppComponent.buildOrGet(this)
        Timber.plant(AndroidTree())
    }

    // region DAGGER

    override fun provideCoreApiDepsProvider(): CoreApiDepsProvider = appComponent

    override fun provideEditorApiDepsProvider(): EditorApiDepsProvider = appComponent

    override fun provideExplorerApiDepsProvider(): ExplorerApiDepsProvider = appComponent

    override fun provideFontsApiDepsProvider(): FontsApiDepsProvider = appComponent

    override fun provideGitApiDepsProvider(): GitApiDepsProvider = appComponent

    override fun provideServersApiDepsProvider(): ServersApiDepsProvider = appComponent

    override fun provideShortcutsApiDepsProvider(): ShortcutsApiDepsProvider = appComponent

    override fun provideThemesApiDepsProvider(): ThemesApiDepsProvider = appComponent

    // endregion
}