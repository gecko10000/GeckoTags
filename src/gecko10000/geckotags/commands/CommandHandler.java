package gecko10000.geckotags.commands;

import gecko10000.geckotags.GeckoTags;
import gecko10000.geckotags.TagGUI;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.strokkur.commands.Aliases;
import net.strokkur.commands.Command;
import net.strokkur.commands.Executes;
import net.strokkur.commands.paper.Executor;
import net.strokkur.commands.permission.Permission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Command("geckotags")
@Aliases("tags")
@Permission("geckotags.command")
public class CommandHandler {

    private final GeckoTags plugin = JavaPlugin.getPlugin(GeckoTags.class);
    private final InternalCommandHandler internalCommandHandler = new InternalCommandHandler();

    public void register() {
        plugin.getLifecycleManager()
                .registerEventHandler(LifecycleEvents.COMMANDS.newHandler(event ->
                        CommandHandlerBrigadier.register(
                                event.registrar()
                        )
                ));
    }

    @Executes
    @Permission("geckotags.command.tags")
    void openGui(CommandSender sender, @Executor Player player) {
        new TagGUI(player);
    }

    @Executes("clear")
    @Permission("geckotags.command.clear")
    void clearTag(CommandSender sender, @Executor Player player) {
        internalCommandHandler.clearTag(player);
    }

    @Executes("set")
    @Permission("geckotags.command.set")
    void setTag(CommandSender sender, @Executor Player player, String tag) {
        internalCommandHandler.setTag(player, tag);
    }

    @Executes("reload")
    @Permission("geckotags.command.reload")
    void reload(CommandSender sender) {
        plugin.reloadConfigs();
        sender.sendRichMessage("<green>Configs reloaded.");
    }

}
