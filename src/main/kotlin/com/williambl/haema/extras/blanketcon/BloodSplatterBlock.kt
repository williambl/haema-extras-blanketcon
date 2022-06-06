package com.williambl.haema.extras.blanketcon

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.material.Material
import net.minecraft.world.level.material.PushReaction
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import java.util.*

object BloodSplatterBlock: DirectionalBlock(Properties.of(Material.DECORATION).noCollission().instabreak()) {
    private val shapes: EnumMap<Direction, VoxelShape> = EnumMap(Direction::class.java)

    init {
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP))
        this.shapes[Direction.UP] = box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0)
        this.shapes[Direction.DOWN] = box(0.0, 15.0, 0.0, 16.0, 16.0, 16.0)
        this.shapes[Direction.NORTH] = box(0.0, 0.0, 15.0, 16.0, 16.0, 16.0)
        this.shapes[Direction.SOUTH] = box(0.0, 0.0, 0.0, 16.0, 16.0, 1.0)
        this.shapes[Direction.EAST] = box(0.0, 0.0, 0.0, 1.0, 16.0, 16.0)
        this.shapes[Direction.WEST] = box(15.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    }

    override fun getShape(state: BlockState, level: BlockGetter, pos: BlockPos, context: CollisionContext): VoxelShape = this.shapes[state.getValue(FACING)] ?: Shapes.block()

    override fun getStateForPlacement(context: BlockPlaceContext): BlockState? {
        val direction = context.clickedFace
        val blockState = context.level.getBlockState(context.clickedPos.relative(direction.opposite))
        return if (blockState.`is`(this) && blockState.getValue(FACING) == direction) this.defaultBlockState().setValue(
            FACING,
            direction.opposite
        ) else this.defaultBlockState().setValue(
            FACING, direction
        )
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block, BlockState>) {
        builder.add(FACING)
    }

    override fun rotate(state: BlockState, rotation: Rotation): BlockState? {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)))
    }

    override fun mirror(state: BlockState, mirror: Mirror): BlockState? {
        return state.setValue(FACING, mirror.mirror(state.getValue(FACING)))
    }

    override fun getPistonPushReaction(state: BlockState?): PushReaction {
        return PushReaction.DESTROY
    }

    override fun canSurvive(state: BlockState, level: LevelReader, pos: BlockPos): Boolean {
        return state.getValue(FACING).let { dir -> pos.relative(dir.opposite).let { basePos -> level.getBlockState(basePos).isFaceSturdy(level, basePos, dir) } }
    }
}