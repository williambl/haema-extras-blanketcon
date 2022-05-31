package com.williambl.haema.extras.blanketcon.client

import com.williambl.haema.extras.blanketcon.HaemaExtrasBlanketcon.id
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.world.phys.Vec3
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.client.ClientModInitializer
import org.quiltmc.qsl.networking.api.client.ClientPlayNetworking

object HaemaExtrasBlanketconClient: ClientModInitializer {
    override fun onInitializeClient(mod: ModContainer) {
        ClientPlayNetworking.registerGlobalReceiver(id("blood_drain")) { client, handler, buf, sender ->
            val centre = Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble())

            while (buf.isReadable(3 * Double.SIZE_BYTES)) {
                val target = Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble())
                client.execute {
                    val delta = centre.subtract(target)
                    val dir = delta.normalize()
                    for (i in 0..10) {
                        val pos = centre.add(delta.scale(client.level?.random?.nextDouble() ?: 1.0))
                        val vel = dir.scale(client.level?.random?.nextDouble() ?: 1.0)
                        client.level?.addParticle(DustParticleOptions.REDSTONE, pos.x, pos.y, pos.z, vel.x, vel.y, vel.z)
                    }
                }
            }
        }
    }
}