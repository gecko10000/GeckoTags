@file:UseSerializers(MMComponentSerializer::class)

package gecko10000.geckotags.configs

import gecko10000.geckolib.config.serializers.MMComponentSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import net.kyori.adventure.text.Component

@Serializable
data class Tag(
    val tag: String,
    val description: List<Component>,
)
