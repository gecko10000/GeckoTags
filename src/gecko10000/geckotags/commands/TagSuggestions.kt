package gecko10000.geckotags.commands

import com.mojang.brigadier.context.CommandContext
import com.mojang.brigadier.suggestion.SuggestionProvider
import com.mojang.brigadier.suggestion.Suggestions
import com.mojang.brigadier.suggestion.SuggestionsBuilder
import gecko10000.geckotags.TagManager
import gecko10000.geckotags.di.MyKoinComponent
import io.papermc.paper.command.brigadier.CommandSourceStack
import org.bukkit.entity.Player
import org.koin.core.component.inject
import java.util.concurrent.CompletableFuture

class TagSuggestions : MyKoinComponent, SuggestionProvider<CommandSourceStack> {

    private val tagManager: TagManager by inject()

    override fun getSuggestions(
        context: CommandContext<CommandSourceStack>,
        builder: SuggestionsBuilder
    ): CompletableFuture<Suggestions> {
        val player = context.source.executor as? Player
        if (player == null) {
            tagManager.getAllTags()
                .map { it.key }
                .forEach(builder::suggest)
        } else {
            tagManager.getUnlockedTags(player)
                .map { it.key }
                .forEach(builder::suggest)
        }
        return builder.buildFuture()
    }

}
