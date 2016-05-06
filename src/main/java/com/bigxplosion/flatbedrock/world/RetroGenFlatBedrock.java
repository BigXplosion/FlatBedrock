package com.bigxplosion.flatbedrock.world;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import com.bigxplosion.flatbedrock.world.retrogen.IRetroGenerator;

public class RetroGenFlatBedrock implements IRetroGenerator {

	@Override
	public String getUniqueGenerationID() {
		return "FlatBedrock:FlatBedrock";
	}

	@Override
	public boolean canGenerateIn(World world, Chunk chunk) {
		return WorldGenFlatBedrock.instance.canGenerate(world, chunk.xPosition, chunk.zPosition);
	}

	@Override
	public void generate(Random rand, World world, int chunkX, int chunkZ) {
		WorldGenFlatBedrock.instance.retroGenerateWorld(world, chunkX, chunkZ);
	}
}
