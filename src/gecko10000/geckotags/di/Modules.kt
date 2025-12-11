package gecko10000.geckotags.di

import gecko10000.geckotags.GeckoTags
import gecko10000.geckotags.TagManager
import org.koin.dsl.module

fun pluginModules(plugin: GeckoTags) = module {
    single { plugin }
    single(createdAtStart = true) { TagManager() }
}
