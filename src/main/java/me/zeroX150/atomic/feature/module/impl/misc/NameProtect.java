/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.feature.module.impl.misc;

import me.zeroX150.atomic.Atomic;
import me.zeroX150.atomic.feature.module.Module;
import me.zeroX150.atomic.feature.module.ModuleType;
import me.zeroX150.atomic.feature.module.config.DynamicValue;
import net.minecraft.client.util.math.MatrixStack;

public class NameProtect extends Module {
    final DynamicValue<String> replacer = this.config.create("Replace name with", "Me");

    public NameProtect() {
        super("Name Protect", "Replaces your username with something else", ModuleType.MISC);
    }

    public String protect(String n) {
        if (!this.isEnabled()) return n;
        return n.replaceAll(Atomic.client.getSession().getUsername(), replacer.getValue());
    }

    @Override
    public void tick() {

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

