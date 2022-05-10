package com.williambl.haema.extras.blanketcon

import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockBehaviour
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.quiltmc.qsl.item.group.api.QuiltItemGroup
import org.slf4j.LoggerFactory

object HaemaExtrasBlanketcon: ModInitializer {
    val LOGGER = LoggerFactory.getLogger(HaemaExtrasBlanketcon::class.java)

    val ITEM_GROUP: CreativeModeTab = QuiltItemGroup.builder(id("blanketcon"))/*.icon { HAEMA_SHOWCASE_TRAVEL_TOKEN.defaultInstance }*/.build()

    override fun onInitialize(mod: ModContainer) {
    }

    fun id(path: String): ResourceLocation = ResourceLocation("haema_extras_blanketcon", path)
}
