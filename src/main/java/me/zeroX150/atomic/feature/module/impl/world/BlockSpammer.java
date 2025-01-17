/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.feature.module.impl.world;

import me.zeroX150.atomic.Atomic;
import me.zeroX150.atomic.feature.module.Module;
import me.zeroX150.atomic.feature.module.ModuleType;
import me.zeroX150.atomic.feature.module.config.SliderValue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.Objects;

public class BlockSpammer extends Module {
    final SliderValue timesPerTick = (SliderValue) this.config.create("Times per tick", 20, 1, 100, 0).description("How many times to interact per tick");

    public BlockSpammer() {
        super("Block Spammer", "uses a block a LOT of times per second", ModuleType.WORLD);
    }

    @Override
    public void tick() {
        HitResult hr = Atomic.client.crosshairTarget;
        if (hr instanceof BlockHitResult bhr) {
            for (int i = 0; i < timesPerTick.getValue(); i++) {
                Objects.requireNonNull(Atomic.client.interactionManager).interactBlock(Atomic.client.player, Atomic.client.world, Hand.MAIN_HAND, bhr);
            }
        }
    }

    @Override
    public void enable() {

    }

    @Override
    public void disable() {

    }

    @Override
    public String getContext() {
        return null;
    }

    @Override
    public void onWorldRender(MatrixStack matrices) {

    }

    @Override
    public void onHudRender() {

    }
}

