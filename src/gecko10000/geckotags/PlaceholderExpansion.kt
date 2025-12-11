package gecko10000.geckotags

import gecko10000.geckotags.di.MyKoinComponent
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import org.koin.core.component.inject

class PlaceholderExpansion : MyKoinComponent, PlaceholderExpansion() {

    private val plugin: GeckoTags by inject()
    private val tagManager: TagManager by inject()

    init {
        register()
    }

    override fun getIdentifier() = "geckotags"

    override fun getAuthor() = plugin.pluginMeta.authors.joinToString()

    override fun getVersion() = plugin.pluginMeta.version

    override fun persist() = true

    override fun onPlaceholderRequest(player: Player, params: String): String {
        if (params != "tag") return ""
        return tagManager.getTag(player)?.let { plugin.config.tags[it]?.tag } ?: ""
    }

    override fun getPlaceholders() = listOf("%${identifier}_tag%")

}
