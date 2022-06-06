package com.williambl.haema.extras.blanketcon

import com.williambl.haema.extras.blanketcon.HaemaExtrasBlanketcon.id
import com.williambl.haema.ritual.craft.RitualAction
import com.williambl.haema.ritual.craft.RitualInventory
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.Tag
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.PlayerLookup
import org.quiltmc.qsl.networking.api.ServerPlayNetworking

object TeleportRitualAction: RitualAction {
    override fun runAction(inv: RitualInventory, data: Tag) {
        if (data !is CompoundTag || !data.contains("x") || !data.contains("y") || !data.contains("z")) {
            return
        }

        ServerPlayNetworking.send(PlayerLookup.tracking(inv.player), id("teleport_fx"), PacketByteBufs.create().also {
            it.writeDouble(inv.player.x)
            it.writeDouble(inv.player.y)
            it.writeDouble(inv.player.z)
        })

        inv.player.teleportToWithTicket(data.getDouble("x"), data.getDouble("y"), data.getDouble("z"))
        inv.player.level.playSound(inv.player, inv.player.x, inv.player.y, inv.player.z, SoundEvents.GENERIC_EXPLODE, SoundSource.NEUTRAL, 1f, 1f)

        ServerPlayNetworking.send(PlayerLookup.tracking(inv.player), id("teleport_fx"), PacketByteBufs.create().also {
            it.writeDouble(inv.player.x)
            it.writeDouble(inv.player.y)
            it.writeDouble(inv.player.z)
        })
    }

    fun showTeleportEffects(level: Level, pos: Vec3) {
        val rand = level.random
        for (i in 0..60) {
            if (i <= 20) {
                level.addParticle(
                    ParticleTypes.LARGE_SMOKE,
                    pos.x + rand.nextGaussian(),
                    pos.y + rand.nextDouble() * 2,
                    pos.z + rand.nextGaussian(),
                    0.0,
                    0.4,
                    0.0
                )
            }

            level.addParticle(
                ParticleTypes.SOUL_FIRE_FLAME,
                pos.x + rand.nextGaussian(),
                pos.y + rand.nextDouble() * 6,
                pos.z + rand.nextGaussian(),
                0.0,
                0.8,
                0.0
            )
        }
    }
}