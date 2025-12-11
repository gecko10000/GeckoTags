package gecko10000.geckotags

import gecko10000.geckolib.config.YamlFileManager
import gecko10000.geckotags.commands.CommandHandler
import gecko10000.geckotags.configs.Config
import gecko10000.geckotags.di.MyKoinContext
import org.bukkit.plugin.java.JavaPlugin

class GeckoTags : JavaPlugin() {

    private val configFile = YamlFileManager(
        configDirectory = dataFolder,
        initialValue = Config(),
        serializer = Config.serializer(),
    )

    val config: Config
        get() = configFile.value

    override fun onEnable() {
        MyKoinContext.init(this)
        CommandHandler().register()
        PlaceholderExpansion()
    }

    fun reloadConfigs() {
        configFile.reload()
    }

}
