package gecko10000.geckotags.commands

import gecko10000.geckolib.extensions.MM
import gecko10000.geckotags.TagManager
import gecko10000.geckotags.di.MyKoinComponent
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.entity.Player
import org.koin.core.component.inject

class InternalCommandHandler : MyKoinComponent {

    private val tagManager: TagManager by inject()

    fun clearTag(player: Player) {
        tagManager.clearTag(player)
        player.sendRichMessage("<green>Tag cleared.")
    }

    fun setTag(player: Player, tag: String) {
        val isActualTag = tagManager.getUnlockedTags(player).containsKey(tag)
        if (!isActualTag) {
            player.sendMessage(
                MM.deserialize(
                    "<red>Tag <tag> not found.",
                    Placeholder.unparsed("tag", tag)
                )
            )
        }
        tagManager.setTag(player, tag)
        player.sendMessage(MM.deserialize("<green>Set your tag to <tag>.", Placeholder.unparsed("tag", tag)))
    }

}
