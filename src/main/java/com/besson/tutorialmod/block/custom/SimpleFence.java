package com.besson.tutorialmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class SimpleFence extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<Type> TYPE = EnumProperty.of("type", Type.class);
    public SimpleFence(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(TYPE, Type.SINGLE));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, TYPE);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return getRelatedBlockState(state, world, pos, state.get(FACING));
    }

    private BlockState getRelatedBlockState(BlockState state, WorldAccess world, BlockPos pos, Direction direction) {
        boolean left = isRelatedBlock(world, pos, direction.rotateYCounterclockwise(), direction) ||
                isRelatedBlock(world, pos, direction.rotateYCounterclockwise(), direction.rotateYCounterclockwise());
        boolean right = isRelatedBlock(world, pos, direction.rotateYClockwise(), direction) ||
                isRelatedBlock(world, pos, direction.rotateYClockwise(), direction.rotateYClockwise());

        if (left && right) {
            return state.with(TYPE, Type.MIDDLE);
        } else if (left) {
            return state.with(TYPE, Type.LEFT);
        } else if (right) {
            return state.with(TYPE, Type.RIGHT);
        } else {
            return state.with(TYPE, Type.SINGLE);
        }
    }

    private boolean isRelatedBlock(WorldAccess world, BlockPos pos, Direction direction, Direction direction1) {
        BlockState state = world.getBlockState(pos.offset(direction));
        if (state.getBlock() == this) {
            Direction blockDirection = state.get(FACING);
            return blockDirection.equals(direction1);
        }
        return false;
    }

    public enum Type implements StringIdentifiable {
        SINGLE("single"),
        LEFT("left"),
        MIDDLE("middle"),
        RIGHT("right");

        private final String name;
        Type(String name) {
            this.name = name;
        }
        @Override
        public String asString() {
            return name;
        }
    }
}
