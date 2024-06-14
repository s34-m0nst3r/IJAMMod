package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import net.minecraft.client.renderer.block.model.ItemOverride;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class SlumberingSandsBiome
{
    public static Biome INSTANCE = SlumberingSandsBiome(new MobSpawnSettings.Builder(), new BiomeGenerationSettings.Builder()).setRegistryName(BaseMod.MODID, "slumberingsandsbiome");

    public static Biome SlumberingSandsBiome(MobSpawnSettings.Builder mobSpawn, BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addBadlandExtraVegetation(builder);
        BiomeDefaultFeatures.addBadlandsTrees(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDesertExtraDecoration(builder);
        BiomeDefaultFeatures.addDesertVegetation(builder);
        BiomeDefaultFeatures.addDesertExtraVegetation(builder);
        BiomeDefaultFeatures.addExtraEmeralds(builder);
        BiomeDefaultFeatures.addJungleMelons(builder);
        BiomeDefaultFeatures.addLightBambooVegetation(builder);
        BiomeDefaultFeatures.addWarmFlowers(builder);

        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);

        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 500, 3, 4));
        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 450, 2, 3));
        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 200, 1, 2));
        mobSpawn.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 200, 1, 2));

        return (new Biome.BiomeBuilder())
                .precipitation(Biome.Precipitation.NONE)
                .biomeCategory(Biome.BiomeCategory.DESERT)
                .temperature(0F)
                .downfall(1F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x3F76E4)
                        .waterFogColor(0x050533)
                        .fogColor(0xC0D8FF)
                        .skyColor(0x00000000)
                        .build()
                ))
                .mobSpawnSettings(mobSpawn.build())
                .generationSettings(builder.build())
                .build();
    }
}