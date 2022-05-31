package com.williambl.haema.extras.blanketcon

import com.williambl.haema.component.EntityVampireComponent
import com.williambl.haema.component.EntityVampireComponent.Companion.goodBloodTag
import com.williambl.haema.component.EntityVampireComponent.Companion.mediumBloodTag
import com.williambl.haema.component.EntityVampireComponent.Companion.poorBloodTag
import com.williambl.haema.component.VampireComponent
import com.williambl.haema.extras.blanketcon.HaemaExtrasBlanketcon.id
import dev.cammiescorner.arcanus.api.spells.AuraType
import dev.cammiescorner.arcanus.api.spells.Spell
import dev.cammiescorner.arcanus.api.spells.SpellComplexity
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.quiltmc.qsl.networking.api.PacketByteBufs
import org.quiltmc.qsl.networking.api.PlayerLookup
import org.quiltmc.qsl.networking.api.ServerPlayNetworking

object BloodDrainSpell : Spell(AuraType.SPECIALIST, SpellComplexity.UNIQUE, 400, false) {
    override fun cast(world: Level, entity: LivingEntity, pos: Vec3) {
        if (!entity.isVampire) {
            return
        }

        val buf = PacketByteBufs.create()

        buf.writeDouble(entity.x)
        buf.writeDouble(entity.y)
        buf.writeDouble(entity.z)

        world.getEntities(entity, entity.boundingBox.inflate(10.0)).forEach { l ->
            if (l is LivingEntity && entity.vampireComponent.feed(l).consumesAction()) {
                buf.writeDouble(l.x)
                buf.writeDouble(l.y)
                buf.writeDouble(l.z)
            }
        }

        ServerPlayNetworking.send(PlayerLookup.tracking(entity), id("blood_drain"), buf)
    }

    val LivingEntity.vampireComponent: VampireComponent
        get() = VampireComponent.entityKey.get(this)

    var LivingEntity.isVampire: Boolean
        get() = VampireComponent.entityKey.getNullable(this)?.isVampire ?: false
        set(value) {
            VampireComponent.entityKey.getNullable(this)?.isVampire = value
        }
}