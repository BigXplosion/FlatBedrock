package com.bigxplosion.flatbedrock.world.retrogen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public interface IRetroGenerator {

	public String getUniqueGenerationID();

	public boolean canGenerateIn(World world, Chunk chunk);

	public void generate(Random rand, World world, int chunkX, int chunkZ);
}
