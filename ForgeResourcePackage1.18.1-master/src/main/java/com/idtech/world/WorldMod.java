package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.*;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistryEntry;

import java.util.List;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class WorldMod {

    // Needed to create "replaceables" for ore feature.
    //To have ores replace non-stone stuff?
    public static final RuleTest DEEPSLATE = new BlockMatchTest(Blocks.DEEPSLATE);
    public static final RuleTest GRASS = new BlockMatchTest(Blocks.GRASS_BLOCK);

    // Testing Ore Generation (https://fabricmc.net/wiki/tutorial:ores)
    private static ConfiguredFeature<?, ?> OVERWORLD_GEL_ORE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            OreFeatures.STONE_ORE_REPLACEABLES,
            GelOreBlock.INSTANCE.defaultBlockState(),
            9));

    public static PlacedFeature OVERWORLD_GEL_ORE_PLACED_FEATURE = OVERWORLD_GEL_ORE_FEATURE.placed(
            List.of(
                    CountPlacement.of(10), // Number of veins per chunk
                    InSquarePlacement.spread(), //Horizontal spreading
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)),
                    BiomeFilter.biome()
            ));

    public static ConfiguredFeature<?, ?> DEEPSLATE_GEL_ORE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            DEEPSLATE,
            DeepslateGelOreBlock.INSTANCE.defaultBlockState(),
            9));

    public static PlacedFeature DEEPSLATE_GEL_ORE_PLACED_FEATURE = DEEPSLATE_GEL_ORE_FEATURE.placed(
            List.of(
                    CountPlacement.of(1),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(80)),
                    BiomeFilter.biome()
            ));

    private static ConfiguredFeature<?, ?> OVERWORLD_IRIDIUM_ORE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            OreFeatures.STONE_ORE_REPLACEABLES,
            IridiumOreBlock.INSTANCE.defaultBlockState(),
            9));

    public static PlacedFeature OVERWORLD_IRIDIUM_ORE_PLACED_FEATURE = OVERWORLD_IRIDIUM_ORE_FEATURE.placed(
            List.of(
                    CountPlacement.of(2), // Number of veins per chunk
                    InSquarePlacement.spread(), //Horizontal spreading
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(10)),
                    BiomeFilter.biome()
            ));

    public static ConfiguredFeature<?, ?> DEEPSLATE_IRIDIUM_ORE_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            DEEPSLATE,
            DeepslateIridiumOreBlock.INSTANCE.defaultBlockState(),
            9));

    public static PlacedFeature DEEPSLATE_IRIDIUM_ORE_PLACED_FEATURE = DEEPSLATE_IRIDIUM_ORE_FEATURE.placed(
            List.of(
                    CountPlacement.of(5),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)),
                    BiomeFilter.biome()
            ));

    public static ConfiguredFeature<?, ?> FUNGAL_GRASS_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            GRASS,
            FungalGrassBlock.INSTANCE.defaultBlockState(),
            9));

    public static PlacedFeature FUNGAL_GRASS_PLACED_FEATURE = FUNGAL_GRASS_FEATURE.placed(
            List.of(
                    CountPlacement.of(3),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(180)),
                    BiomeFilter.biome()
            ));

    // For some reason you need to create this resource key to register biomes idk
    //private static ResourceKey<Biome> BASIC_TESTER = ResourceKey.create(Registry.BIOME_REGISTRY, CustomBiomes.BASIC_TESTER.getRegistryName());
    private static ResourceKey<Biome> SLUMBERING_SANDS = ResourceKey.create(Registry.BIOME_REGISTRY, SlumberingSandsBiome.INSTANCE.getRegistryName());

    public static void registerBiomes(final RegistryEvent.Register<Biome> event){
        event.getRegistry().register(SlumberingSandsBiome.INSTANCE);

    }
    public static void setupBiomes()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(SLUMBERING_SANDS, 9999));
    }

    //Adds our features to the world?
    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {

        //Allows us to edit the biome generation event???
        BiomeGenerationSettingsBuilder biomeGen = event.getGeneration();

        FeatureUtils.register("gel_ore_feature", OVERWORLD_GEL_ORE_FEATURE);
        PlacementUtils.register("gel_ore_feature", OVERWORLD_GEL_ORE_PLACED_FEATURE);
        FeatureUtils.register("iridium_ore_feature", OVERWORLD_IRIDIUM_ORE_FEATURE);
        PlacementUtils.register("iridium_ore_feature", OVERWORLD_IRIDIUM_ORE_PLACED_FEATURE);

        FeatureUtils.register("deepslate_gel_ore_feature", DEEPSLATE_GEL_ORE_FEATURE);
        PlacementUtils.register("deepslate_gel_ore_feature", DEEPSLATE_GEL_ORE_PLACED_FEATURE);
        FeatureUtils.register("deepslate_iridium_ore_feature", DEEPSLATE_IRIDIUM_ORE_FEATURE);
        PlacementUtils.register("deepslate_iridium_ore_feature", DEEPSLATE_IRIDIUM_ORE_PLACED_FEATURE);

        FeatureUtils.register("fungal_grass_feature", FUNGAL_GRASS_FEATURE);
        PlacementUtils.register("fungal_grass_feature", FUNGAL_GRASS_PLACED_FEATURE);

        // Spawn ores
        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_GEL_ORE_PLACED_FEATURE);
        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OVERWORLD_IRIDIUM_ORE_PLACED_FEATURE);
        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_GEL_ORE_PLACED_FEATURE);
        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, DEEPSLATE_IRIDIUM_ORE_PLACED_FEATURE);

        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FUNGAL_GRASS_FEATURE.onlyWhenEmpty());
        biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, FUNGAL_GRASS_PLACED_FEATURE);
    }
}

