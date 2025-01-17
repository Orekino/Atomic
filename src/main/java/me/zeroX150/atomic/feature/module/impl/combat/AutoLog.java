/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.feature.module.impl.combat;

import me.zeroX150.atomic.Atomic;
import me.zeroX150.atomic.feature.module.Module;
import me.zeroX150.atomic.feature.module.ModuleType;
import me.zeroX150.atomic.feature.module.config.DynamicValue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class AutoLog extends Module {
    final DynamicValue<Integer> hpAmount = this.config.create("Health", 3).description("The amount of HP needed to issue a disconnect");

    public AutoLog() {
        super("Auto Log", "automatically leaves after a certain health is reached", ModuleType.COMBAT);
    }

    @Override
    public void tick() {
        if (Atomic.client.player == null || Atomic.client.getNetworkHandler() == null) return;
        float currentHealth = Atomic.client.player.getHealth();
        if (currentHealth < hpAmount.getValue()) {
            Atomic.client.getNetworkHandler().getConnection().disconnect(Text.of("[A] Autolog disabled because " + ((int) currentHealth) + " < " + hpAmount.getValue()));
            toggle();
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
        return hpAmount.getValue() + "";
    }

    @Override
    public void onWorldRender(MatrixStack matrices) {

    }

    @Override
    public void onHudRender() {

    }
}

