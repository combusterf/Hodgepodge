package com.mitchej123.hodgepodge.core;

import com.mitchej123.hodgepodge.core.util.BlockMatcher;
import com.mitchej123.hodgepodge.mixins.TargetedMod;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Arrays;

public class LoadingConfig {
    public String[] requiredMods;
    public boolean requiredModsInDev;
    
    // Adjustments
    public int ic2SeedMaxStackSize;
    public int particleLimit;
    
    // Mixins
    public boolean addCVSupportToWandPedestal;
    public boolean dropPickedLootOnDespawn;
    public boolean fixFenceConnections;
    public boolean fixFireSpread;
    public boolean fixGetBlockLightValue;
    public boolean fixGuiGameOver;
    public boolean fixHopperHitBox;
    public boolean fixHungerOverhaul;
    public boolean fixIc2DirectInventoryAccess;
    public boolean fixIc2Hazmat;
    public boolean fixIc2Nightvision;
    public boolean fixIc2ReactorDupe;
    public boolean fixIc2UnprotectedGetBlock;
    public boolean fixNorthWestBias;
    public boolean fixPotionEffectRender;
    public boolean fixThaumcraftUnprotectedGetBlock;
    public boolean fixUrlDetection;
    public boolean fixVanillaUnprotectedGetBlock;
    public boolean fixWorldGetBlock;
    public boolean fixDimensionChangeHearts;
    public boolean increaseParticleLimit;
    public boolean hideIc2ReactorSlots;
    public boolean installAnchorAlarm;
    public boolean preventPickupLoot;
    public boolean removeUpdateChecks;
    public boolean speedupChunkCoordinatesHashCode;
    public boolean speedupProgressBar;
    public boolean speedupVanillaFurnace;
    
    // ASM
    public boolean pollutionAsm;
    public boolean cofhWorldTransformer;
    public boolean enableTileRendererProfiler;
    public boolean biblocraftRecipes;
    
    public String thermosCraftServerClass;
        
    
    public static Configuration config;

    public static BlockMatcher standardBlocks = new BlockMatcher();
    public static BlockMatcher liquidBlocks = new BlockMatcher();
    public static BlockMatcher doublePlants = new BlockMatcher();
    public static BlockMatcher crossedSquares = new BlockMatcher();
    public static BlockMatcher blockVine = new BlockMatcher();


    public LoadingConfig(File file) {
        config = new Configuration(file);
        
        requiredMods = config.get("overall", "requiredMods", defaultRequiredMods, "Subset of TargetMods that are required").getStringList();
        requiredModsInDev = config.get("overall", "requiredModsInDev", false, "Require the Required Mods in dev").getBoolean();
                
        fixWorldGetBlock = config.get("fixes", "fixWorldGetBlock", true, "Fix unprotected getBlock() in World").getBoolean();
        fixNorthWestBias = config.get("fixes", "fixNorthWestBias", true, "Fix northwest bias on RandomPositionGenerator").getBoolean();
        fixFenceConnections = config.get("fixes", "fixFenceConnections", true, "Fix fence connections with other types of fence").getBoolean();
        fixIc2DirectInventoryAccess = config.get("fixes", "fixIc2DirectInventoryAccess", true, "Fix IC2's direct inventory access").getBoolean();
        fixIc2ReactorDupe = config.get("fixes", "fixIc2ReactorDupe", true, "Fix IC2's reactor dupe").getBoolean();
        fixIc2Nightvision = config.get("fixes", "fixIc2Nightvision", true, "Prevent IC2's nightvision from blinding you").getBoolean();
        fixIc2Hazmat = config.get("fixes", "fixIc2Hazmat", true,"Fix IC2 armors to avoid giving poison").getBoolean();
        fixVanillaUnprotectedGetBlock = config.get("fixes", "fixVanillaUnprotectedGetBlock", true, "Fixes various unchecked vanilla getBlock() methods").getBoolean();
        fixIc2UnprotectedGetBlock = config.get("fixes", "fixIc2UnprotectedGetBlock", true, "Fixes various unchecked IC2 getBlock() methods").getBoolean();
        fixThaumcraftUnprotectedGetBlock = config.get("fixes", "fixThaumcraftUnprotectedGetBlock", true, "Various Thaumcraft unchecked getBlock() patches").getBoolean();
        fixHungerOverhaul = config.get("fixes", "fixHungerOverhaul", true, "Fix hunger overhaul low stat effects").getBoolean();
        removeUpdateChecks = config.get("fixes", "removeUpdateChecks", true, "Remove old/stale/outdated update checks.").getBoolean();
        fixGuiGameOver = config.get("fixes", "fixGuiGameOver", true, "Fix Game Over GUI buttons disabled if switching fullscreen").getBoolean();
        fixHopperHitBox = config.get("fixes", "fixHopperHitBox", true, "Fix vanilla hopper hit box").getBoolean();
        fixGetBlockLightValue = config.get("fixes", "fixGetBlockLightValue", true, "Fix vanilla light calculation sometimes cause NPE on thermos").getBoolean();
        fixFireSpread = config.get("fixes", "fixFireSpread", true, "Fix vanilla fire spread sometimes cause NPE on thermos").getBoolean();
        fixUrlDetection = config.get("fixes", "fixUrlDetection", true, "Fix URISyntaxException in forge.").getBoolean();
        fixDimensionChangeHearts = config.get("fixes", "fixDimensionChangeHearts", true, "Fix losing bonus hearts on dimension change").getBoolean();
        
        increaseParticleLimit = config.get("tweaks", "increaseParticleLimit", true, "Increase particle limit").getBoolean();
        particleLimit = Math.max(Math.min(config.get("tweaks", "particleLimit", 8000, "Particle limit [4000-16000]").getInt(), 16000), 4000);
        fixPotionEffectRender = config.get("tweaks", "fixPotionEffectRender", true, "Move vanilla potion effect status rendering before everything else").getBoolean();
        installAnchorAlarm = config.get("tweaks", "installAnchorAlarm", true, "Wake up passive & personal anchors on player login").getBoolean();
        preventPickupLoot = config.get("tweaks", "preventPickupLoot", true, "Prevent monsters from picking up loot.").getBoolean();
        dropPickedLootOnDespawn = config.get("tweaks", "dropPickedLootOnDespawn", true, "Drop picked loot on entity despawn").getBoolean();
        hideIc2ReactorSlots = config.get("tweaks", "hideIc2ReactorSlots", true, "Prevent IC2's reactor's coolant slots from being accessed by automations if not a fluid reactor").getBoolean();
        enableTileRendererProfiler = config.get("tweaks", "enableTileRendererProfiler", true, "Shows renderer's impact on FPS in vanilla lagometer").getBoolean();
        addCVSupportToWandPedestal = config.get("tweaks", "addCVSupportToWandPedestal", true, "Add CV support to Thaumcraft wand recharge pedestal").getBoolean();

        ic2SeedMaxStackSize = config.get("tweaks", "ic2SeedMaxStackSize", 64, "IC2 seed max stack size").getInt();
        
        speedupChunkCoordinatesHashCode = config.get("speedups", "speedupChunkCoordinatesHashCode", true, "Speedup ChunkCoordinates hashCode").getBoolean();
        speedupVanillaFurnace = config.get("speedups", "speedupVanillaFurnace", true, "Speedup Vanilla Furnace recipe lookup").getBoolean();
        
        speedupProgressBar = config.get("asm", "speedupProgressBar", true, "Speedup progressbar").getBoolean();
        pollutionAsm = config.get("asm", "pollutionAsm", true, "Enable pollution rendering ASM").getBoolean();
        cofhWorldTransformer = config.get("asm", "cofhWorldTransformer", true, "Enable Glease's ASM patch to disable unused CoFH tileentity cache").getBoolean();
        biblocraftRecipes = config.get("asm", "biblocraftRecipes", true, "Remove recipe generation from BiblioCraft").getBoolean();
        
        thermosCraftServerClass = config.get("asm", "thermosCraftServerClass", "org.bukkit.craftbukkit.v1_7_R4.CraftServer", "If using Bukkit/Thermos, the CraftServer package.").getString();

        if (config.hasChanged())
            config.save();
    }

    public static void postInitClient() {
        // need to be done later cause it initializes classes
        if (config == null) {
            System.err.println("Didn't load HODGEPODGE");
            config = new Configuration(new File(Launch.minecraftHome, "config/hodgepodge.cfg"));
        }


        standardBlocks.updateClassList(config.get("pollutionrecolor", "renderStandardBlock", defaultPollutionRenderStandardBlock).getStringList());
        liquidBlocks.updateClassList(config.get("pollutionrecolor", "renderBlockLiquid", defaultPollutionRenderLiquidBlocks).getStringList());
        doublePlants.updateClassList(config.get("pollutionrecolor", "renderBlockDoublePlant", defaultPollutionRenderDoublePlant).getStringList());
        crossedSquares.updateClassList(config.get("pollutionrecolor", "renderCrossedSquares", defaultPollutionRenderCrossedSquares).getStringList());
        blockVine.updateClassList(config.get("pollutionrecolor", "renderblockVine", defaultPollutionRenderblockVine).getStringList());

        config.getCategory("pollutionrecolor").setComment(pollutionRecolorComment);

        if (config.hasChanged())
            config.save();
    }

    /*
     * Defaults
     */

    // Default to all mods being required
    private static final String[] defaultRequiredMods = Arrays.stream(TargetedMod.values()).map(f -> f.modName).toArray(String[]::new);
    public static final String[] defaultPollutionRenderStandardBlock = new String[]{
        "net.minecraft.block.BlockGrass:GRASS",
        "net.minecraft.block.BlockLeavesBase:LEAVES",
        "biomesoplenty.common.blocks.BlockOriginGrass:GRASS",
        "biomesoplenty.common.blocks.BlockLongGrass:GRASS",
        "biomesoplenty.common.blocks.BlockNewGrass:GRASS",
        "tconstruct.blocks.slime.SlimeGrass:GRASS",
        "thaumcraft.common.blocks.BlockMagicalLeaves:LEAVES",
    };

    public static final String[] defaultPollutionRenderLiquidBlocks = new String[]{
        "net.minecraft.block.BlockLiquid:LIQUID",
    };

    public static final String[] defaultPollutionRenderDoublePlant = new String[]{
        "net.minecraft.block.BlockDoublePlant:FLOWER",
    };

    public static final String[] defaultPollutionRenderCrossedSquares = new String[]{
        "net.minecraft.block.BlockTallGrass:FLOWER",
        "net.minecraft.block.BlockFlower:FLOWER",
        "biomesoplenty.common.blocks.BlockBOPFlower:FLOWER",
        "biomesoplenty.common.blocks.BlockBOPFlower2:FLOWER",
        "biomesoplenty.common.blocks.BlockBOPFoliage:FLOWER",
    };
    public static final String[] defaultPollutionRenderblockVine = new String[]{
        "net.minecraft.block.BlockVine:FLOWER",
    };
    
    public static final String pollutionRecolorComment =  "Blocks that should be colored by pollution. \n" +
        "\tGrouped by the render type. \n" +
        "\tFormat: [BlockClass]:[colortype] \n" +
        "\tValid types: GRASS, LEAVES, FLOWER, LIQUID \n" +
        "\tAdd [-] first to blacklist.";

}
