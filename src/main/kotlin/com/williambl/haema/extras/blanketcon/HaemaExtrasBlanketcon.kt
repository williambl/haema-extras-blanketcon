package com.williambl.haema.extras.blanketcon

import com.williambl.haema.ritual.RitualModule
import dev.cammiescorner.arcanus.Arcanus
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.item.group.api.QuiltItemGroup
import org.slf4j.LoggerFactory

object HaemaExtrasBlanketcon: ModInitializer {
    val LOGGER = LoggerFactory.getLogger(HaemaExtrasBlanketcon::class.java)

    val ITEM_GROUP: CreativeModeTab = QuiltItemGroup.builder(id("blanketcon")).icon { HAEMA_BOOTH_TRAVEL_TOKEN.defaultInstance }.build()

    val HAEMA_BOOTH_TRAVEL_TOKEN: Item = Registry.register(Registry.ITEM, id("haema_booth_travel_token"), Item(Item.Properties().tab(ITEM_GROUP).stacksTo(1).rarity(Rarity.RARE)))
    val CAMPSITE_TRAVEL_TOKEN: Item = Registry.register(Registry.ITEM, id("campsite_travel_token"), Item(Item.Properties().tab(ITEM_GROUP).stacksTo(1).rarity(Rarity.RARE)))

    val TELEPORT_RITUAL_ACTION: TeleportRitualAction = Registry.register(RitualModule.RITUAL_ACTION_REGISTRY, id("teleport"), TeleportRitualAction)

    val BLOOD_DRAIN_SPELL: BloodDrainSpell = Registry.register(Arcanus.SPELL, id("blood_drain"), BloodDrainSpell)

    override fun onInitialize(mod: ModContainer) {
    }

    fun id(path: String): ResourceLocation = ResourceLocation("haema_extras_blanketcon", path)
}
