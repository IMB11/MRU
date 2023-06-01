package com.mineblock11.mru.biome;

import net.minecraft.world.biome.Biome;

@FunctionalInterface
public interface BiomePredicate {
    boolean satisfies(Biome biomeToCheck);
}
