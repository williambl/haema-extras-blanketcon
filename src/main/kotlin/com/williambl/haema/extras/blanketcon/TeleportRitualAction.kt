package com.williambl.haema.extras.blanketcon

import com.williambl.haema.ritual.craft.RitualAction
import com.williambl.haema.ritual.craft.RitualInventory
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag

object TeleportRitualAction: RitualAction {
    override fun runAction(inv: RitualInventory, data: Tag) {
        if (data !is CompoundTag || !data.contains("x") || !data.contains("y") || !data.contains("z")) {
            return
        }

        inv.player.teleportToWithTicket(data.getDouble("x"), data.getDouble("y"), data.getDouble("z"))
    }
}