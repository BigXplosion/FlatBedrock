package com.bigxplosion.flatbedrock.config;

import java.io.File;

import net.minecraftforge.fml.relauncher.FMLInjectionData;

public class Config {

	public static final File mcDir = (File) FMLInjectionData.data()[6];
	public static final File configLocation = new File(mcDir, "config" + File.separator + "FlatBedrock");
}
