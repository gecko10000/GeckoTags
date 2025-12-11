package gecko10000.geckotags

import gecko10000.geckotags.configs.Tag
import gecko10000.geckotags.di.MyKoinComponent
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.koin.core.component.inject

class TagManager : MyKoinComponent {

    private companion object {
        const val PERM_PREFIX = "geckotags.tag."
    }

    private val plugin: GeckoTags by inject()

    private val tagKey = NamespacedKey(plugin, "tag")

    fun getTag(player: Player): String? {
        return player.persistentDataContainer.get(tagKey, PersistentDataType.STRING)
    }

    fun setTag(player: Player, tagId: String) {
        player.persistentDataContainer.set(tagKey, PersistentDataType.STRING, tagId)
    }

    fun clearTag(player: Player) {
        player.persistentDataContainer.remove(tagKey)
    }

    fun getUnlockedTags(player: Player): Map<String, Tag> {
        return plugin.config.tags
            .filter { player.hasPermission("$PERM_PREFIX${it.key}") }
    }

    fun getAllTags() = plugin.config.tags

}
