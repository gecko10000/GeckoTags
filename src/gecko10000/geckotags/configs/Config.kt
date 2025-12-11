@file:UseSerializers(MMComponentSerializer::class)

package gecko10000.geckotags.configs

import gecko10000.geckolib.config.serializers.MMComponentSerializer
import gecko10000.geckolib.extensions.MM
import gecko10000.geckolib.extensions.parseMM
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import net.kyori.adventure.text.Component

@Serializable
data class Config(
    val guiTitle: Component = parseMM("<dark_aqua>Tags"),
    val tags: Map<String, Tag> = mapOf("starter" to Tag("Hello", listOf(MM.deserialize("World")))),
)
