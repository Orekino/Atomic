/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.feature.module.impl.render.oreSim;

import me.zeroX150.atomic.feature.module.config.BooleanValue;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Ore {
    private static final BooleanValue coal = new BooleanValue("Coal", false);
    private static final BooleanValue iron = new BooleanValue("Iron", false);
    private static final BooleanValue gold = new BooleanValue("Gold", false);
    private static final BooleanValue redstone = new BooleanValue("Redstone", false);
    private static final BooleanValue diamond = new BooleanValue("Diamond", true);
    private static final BooleanValue lapis = new BooleanValue("Lapis", false);
    private static final BooleanValue copper = new BooleanValue("Kappa", false);
    private static final BooleanValue emerald = new BooleanValue("Emerald", false);
    private static final BooleanValue quartz = new BooleanValue("Quartz", false);
    private static final BooleanValue debris = new BooleanValue("Ancient Debris", false);
    public static final List<BooleanValue> oreSettings = new ArrayList<>(Arrays.asList(coal, iron, gold, redstone, diamond, lapis, copper, emerald, quartz, debris));
    public final Type type;
    public final String dimension;
    public final HashMap<String, Integer> index;
    public final boolean depthAverage;
    public final Generator generator;
    public final int size;
    public final BooleanValue enabled;
    public final Color color;
    public int step;
    public IntProvider count;
    public int minY;
    public int maxY;
    public long paperSeed;

    Ore(Type type, String dimension, HashMap<String, Integer> index, int step, IntProvider count, boolean depthAverage, int minY, int maxY, Generator generator, int size, BooleanValue enabled, Color color) {
        this.type = type;
        this.dimension = dimension;
        this.index = index;
        this.step = step;
        this.count = count;
        this.depthAverage = depthAverage;
        this.minY = minY;
        this.maxY = maxY;
        this.generator = generator;
        this.size = size;
        this.enabled = enabled;
        this.color = color;
    }

    Ore(Type type, String dimension, int index, int step, IntProvider count, boolean depthAverage, int minY, int maxY, @SuppressWarnings("SameParameterValue") Generator generator, int size, BooleanValue enabled, Color color) {
        this(type, dimension, indexToMap(index), step, count, depthAverage, minY, maxY, generator, size, enabled, color);
    }

    private static HashMap<String, Integer> indexToMap(int index) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("default", index);
        return map;
    }

    private static List<Ore> baseConfig() {
        List<Ore> ores = new ArrayList<>();
        HashMap<String, Integer> emeraldIndexes = new HashMap<>();
        emeraldIndexes.put("default", -1);
        String[] emeraldBiomes = new String[]{ "mountains", "mountain_edge", "wooded_mountains", "gravelly_mountains", "modified_gravelly_mountains", "paper" };
        for (String emeraldBiome : emeraldBiomes) {
            emeraldIndexes.put(emeraldBiome, 17);
        }
        HashMap<String, Integer> LDebrisIndexes = new HashMap<>();
        LDebrisIndexes.put("default", 15);
        LDebrisIndexes.put("crimson_forest", 12);
        LDebrisIndexes.put("warped_forest", 13);
        HashMap<String, Integer> SDebrisIndexes = new HashMap<>();
        LDebrisIndexes.forEach((biome, index) -> SDebrisIndexes.put(biome, index + 1));
        HashMap<String, Integer> extraGoldIndexes = new HashMap<>();
        extraGoldIndexes.put("default", -1);
        String[] extraGoldBiomes = new String[]{ "badlands", "badlands_plateau", "modified_badlands_plateau", "wooded_badlands_plateau", "modified_wooded_badlands_plateau", "eroded_badlands", "paper" };
        for (String extraGoldBiome : extraGoldBiomes) {
            extraGoldIndexes.put(extraGoldBiome, 14);
        }
        ores.add(new Ore(Type.COAL, "overworld", 7, 6, ConstantIntProvider.create(20), false, 0, 128, Generator.DEFAULT, 20, coal, new Color(47, 44, 54)));
        ores.add(new Ore(Type.IRON, "overworld", 8, 6, ConstantIntProvider.create(20), false, 0, 64, Generator.DEFAULT, 9, iron, new Color(236, 173, 119)));
        ores.add(new Ore(Type.GOLD, "overworld", 9, 6, ConstantIntProvider.create(2), false, 0, 32, Generator.DEFAULT, 9, gold, new Color(247, 229, 30)));
        ores.add(new Ore(Type.REDSTONE, "overworld", 10, 6, ConstantIntProvider.create(8), false, 0, 16, Generator.DEFAULT, 8, redstone, new Color(245, 7, 23)));
        ores.add(new Ore(Type.DIAMOND, "overworld", 11, 6, ConstantIntProvider.create(1), false, 0, 16, Generator.DEFAULT, 8, diamond, new Color(33, 244, 255)));
        ores.add(new Ore(Type.LAPIS, "overworld", 12, 6, ConstantIntProvider.create(1), true, 16, 16, Generator.DEFAULT, 7, lapis, new Color(8, 26, 189)));
        ores.add(new Ore(Type.COPPER, "overworld", 13, 6, ConstantIntProvider.create(6), true, 49, 49, Generator.DEFAULT, 10, copper, new Color(239, 151, 0)));
        ores.add(new Ore(Type.GOLD_EXTRA, "overworld", extraGoldIndexes, 6, ConstantIntProvider.create(20), false, 32, 80, Generator.DEFAULT, 9, gold, new Color(247, 229, 30)));
        ores.add(new Ore(Type.EMERALD, "overworld", emeraldIndexes, 6, UniformIntProvider.create(6, 8), false, 4, 32, Generator.EMERALD, 1, emerald, new Color(27, 209, 45)));
        ores.add(new Ore(Type.GOLD_NETHER, "nether", 13, 7, ConstantIntProvider.create(10), false, 10, 118, Generator.DEFAULT, 10, gold, new Color(247, 229, 30)));
        ores.add(new Ore(Type.QUARTZ, "nether", 14, 7, ConstantIntProvider.create(16), false, 10, 118, Generator.DEFAULT, 14, quartz, new Color(205, 205, 205)));
        ores.add(new Ore(Type.LDEBRIS, "nether", LDebrisIndexes, 7, ConstantIntProvider.create(1), true, 17, 9, Generator.NO_SURFACE, 3, debris, new Color(209, 27, 245)));
        ores.add(new Ore(Type.SDEBRIS, "nether", SDebrisIndexes, 7, ConstantIntProvider.create(1), false, 8, 120, Generator.NO_SURFACE, 2, debris, new Color(209, 27, 245)));
        return ores;
    }

    public static void setPaperSeed(long paperSeed, List<Ore> oreList) {
        Random rand = new Random(paperSeed ^ 0x5DEECE66DL);
        int prevIndex = -4;
        for (Ore ore : oreList) {
            if (!ore.dimension.endsWith("overworld")) continue;

            int index;
            if (ore.index.containsKey("paper")) {
                index = ore.index.get("paper");
            } else {
                index = ore.index.get("default");
            }

            for (; prevIndex <= index; prevIndex++) {
                ore.paperSeed = rand.nextLong();
            }
        }

    }

    private static List<Ore> V1_17_1() {
        return baseConfig();
    }

    private static List<Ore> V1_17() {
        List<Ore> ores = baseConfig();
        for (Ore ore : ores) {
            if (ore.type.equals(Type.DIAMOND))
                ore.maxY = 17;
            if (ore.type.equals(Type.EMERALD))
                ore.count = UniformIntProvider.create(6, 24);
        }
        return ores;
    }

    private static List<Ore> V1_16() {
        List<Ore> ores = baseConfig();
        ores.removeIf(ore -> ore.type.equals(Type.COPPER));
        for (Ore ore : ores) {
            if (ore.type == Type.EMERALD || ore.type == Type.GOLD_EXTRA) {
                ore.index.keySet().forEach(key -> ore.index.put(key, ore.index.get(key) - 3));
            } else if (ore.dimension.equals("overworld")) {
                ore.index.keySet().forEach(key -> ore.index.put(key, ore.index.get(key) - 2));
            } else if (ore.type == Type.LDEBRIS) {
                ore.minY = 16;
                ore.maxY = 8;
            }
        }
        return ores;
    }

    private static List<Ore> V1_15() {
        List<Ore> ores = V1_16();
        ores.removeIf(ore -> ore.type == Type.SDEBRIS || ore.type == Type.LDEBRIS || ore.type == Type.GOLD_NETHER);
        for (Ore ore : ores) {
            ore.step -= 2;
        }
        return ores;
    }

    protected static List<Ore> getConfig(String version) {
        return switch (version) {
            case "1.17.1" -> V1_17_1();
            case "1.17.0" -> V1_17();
            case "1.16" -> V1_16();
            case "1.15", "1.14" -> V1_15();

            default -> throw new IllegalStateException("Unknown version: " + version);
        };
    }

    protected enum Type {
        DIAMOND, REDSTONE, GOLD, IRON, COAL, EMERALD, SDEBRIS, LDEBRIS, LAPIS, COPPER, QUARTZ, GOLD_NETHER, GOLD_EXTRA
    }

    protected enum Generator {
        DEFAULT, EMERALD, NO_SURFACE
    }
}
