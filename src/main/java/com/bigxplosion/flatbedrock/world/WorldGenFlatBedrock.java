package com.bigxplosion.flatbedrock.world;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;

import net.minecraftforge.fml.common.IWorldGenerator;

import com.bigxplosion.flatbedrock.custom.CustomDimensionManager;

public class WorldGenFlatBedrock implements IWorldGenerator {

	public static WorldGenFlatBedrock instance = new WorldGenFlatBedrock();

	protected Map<Integer, CustomDimensionManager.DimensionEntry> dimensions = Maps.newHashMap(CustomDimensionManager.getDimensions());

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
	{
		if (canGenerate(world, chunkX, chunkZ))
			generateWorld(world, chunkX, chunkZ);
	}

	public boolean canGenerate(World world, int chunkX, int chunkZ)
	{
		return world.getWorldType() != WorldType.FLAT;
	}

	public void generateWorld(World world, int chunkX, int chunkZ)
	{
		int id = world.provider.getDimension();

		if (dimensions.containsKey(id))
		{
			CustomDimensionManager.DimensionEntry dimension = dimensions.get(id);

			if (dimension.genBottom)
				generateBottom(world, chunkX, chunkZ, Block.REGISTRY.getObject(new ResourceLocation(dimension.fillBlock)), false);

			if (dimension.genTop)
				generateTop(world, chunkX, chunkZ, Block.REGISTRY.getObject(new ResourceLocation(dimension.fillBlock)), false);
		}
	}

	public void retroGenerateWorld(World world, int chunkX, int chunkZ)
	{
		int id = world.provider.getDimension();

		if (dimensions.containsKey(id))
		{
			CustomDimensionManager.DimensionEntry dimension = dimensions.get(id);

			if (dimension.retroGenBottom)
				generateBottom(world, chunkX, chunkZ, Block.REGISTRY.getObject(new ResourceLocation(dimension.fillBlock)), true);

			if (dimension.retroGenTop)
				generateTop(world, chunkX, chunkZ, Block.REGISTRY.getObject(new ResourceLocation(dimension.fillBlock)), true);
		}
	}

	public void generateTop(World world, int chunkX, int chunkZ, Block block, boolean retro)
	{
		int flag = retro ? 3 : 2;
		for (int blockX = 0; blockX < 16; blockX++)
			for (int blockZ = 0; blockZ < 16; blockZ++)
				for (int blockY = 126; blockY > 121; blockY--) {
					BlockPos target = new BlockPos(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ);
					if (world.getBlockState(target).getBlock() == Blocks.BEDROCK)
						world.setBlockState(target, block.getDefaultState(), flag);
				}
	}

	public void generateBottom(World world, int chunkX, int chunkZ, Block block, boolean retro)
	{
		int flag = retro ? 3 : 2;
		for (int blockX = 0; blockX < 16; blockX++)
			for (int blockZ = 0; blockZ < 16; blockZ++)
				for (int blockY = 5; blockY > 0; blockY--) {
					BlockPos target = new BlockPos(chunkX * 16 + blockX, blockY, chunkZ * 16 + blockZ);
					if (world.getBlockState(target).getBlock() == Blocks.BEDROCK)
						world.setBlockState(target, block.getDefaultState(), flag);
				}
	}
}
