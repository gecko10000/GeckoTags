package gecko10000.geckotags

import gecko10000.geckolib.extensions.MM
import gecko10000.geckolib.extensions.parseMM
import gecko10000.geckolib.extensions.withDefaults
import gecko10000.geckolib.inventorygui.GUI
import gecko10000.geckolib.inventorygui.InventoryGUI
import gecko10000.geckolib.inventorygui.ItemButton
import gecko10000.geckolib.misc.ItemUtils
import gecko10000.geckotags.configs.Tag
import gecko10000.geckotags.di.MyKoinComponent
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.koin.core.component.inject

class TagGUI(player: Player) : GUI(player), MyKoinComponent {

    private val tagManager: TagManager by inject()

    private val plugin: GeckoTags by inject()

    override fun createInventory(): InventoryGUI {
        val tags = tagManager.getUnlockedTags(player)
        val size = ItemUtils.minimumChestSize(tags.size + 1)
        val inventory = InventoryGUI(plugin.server.createInventory(this, size, plugin.config.guiTitle))
        inventory.fill(0, size, FILLER)
        tags.entries.forEachIndexed { i, e ->
            inventory.addButton(tagButton(e.key to e.value), i)
        }
        inventory.addButton(clearButton(), tags.size)
        return inventory
    }

    private fun tagButton(tag: Pair<String, Tag>): ItemButton {
        val item = ItemStack.of(Material.NAME_TAG)
        item.editMeta {
            it.itemName(MM.deserialize(tag.second.tag))
            it.lore(tag.second.description.map { it.withDefaults() })
            if (tagManager.getTag(player) == tag.first) {
                it.setEnchantmentGlintOverride(true)
            }
        }
        return ItemButton.create(item) { e ->
            tagManager.setTag(player, tag.first)
            TagGUI(player)
        }
    }

    private fun clearButton(): ItemButton {
        val cleared = tagManager.getTag(player) == null

        val item = ItemStack.of(Material.BARRIER)
        item.editMeta {
            it.itemName(parseMM("<red>Clear${if (cleared) "ed" else ""}"))
            it.setEnchantmentGlintOverride(cleared)
        }
        return ItemButton.create(item) { e ->
            tagManager.clearTag(player)
            TagGUI(player)
        }
    }

}
