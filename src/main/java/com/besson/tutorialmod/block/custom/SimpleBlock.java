package com.besson.tutorialmod.block.custom;

import com.besson.tutorialmod.particle.ModParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class SimpleBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final VoxelShape SHAPE_N = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 2, 2, 16, 14, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SHAPE_W = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(2, 2, 0, 16, 14, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SHAPE_S = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 2, 0, 16, 14, 14)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public static final VoxelShape SHAPE_E = Stream.of(
            Block.createCuboidShape(0, 0, 0, 16, 2, 16),
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 2, 0, 14, 14, 16)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();
    public SimpleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case WEST -> SHAPE_W;
            case SOUTH -> SHAPE_S;
            case EAST -> SHAPE_E;
            default -> SHAPE_N;
        };
    }

    @Override
    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        float f = 0.25f;
        if (random.nextFloat() < f) {
            world.addParticle(ModParticles.GREEN_FLAME,
                    pos.getX() + random.nextDouble(), pos.getY() + random.nextDouble(), pos.getZ() + random.nextDouble(),
                    0, 0.1, 0);
        }
        super.randomDisplayTick(state, world, pos, random);
    }
}
