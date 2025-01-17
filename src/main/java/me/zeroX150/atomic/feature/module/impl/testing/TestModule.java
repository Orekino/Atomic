/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.feature.module.impl.testing;

import me.zeroX150.atomic.feature.gui.hud.HudRenderer;
import me.zeroX150.atomic.feature.module.Module;
import me.zeroX150.atomic.feature.module.ModuleType;
import me.zeroX150.atomic.helper.font.FontRenderers;
import net.minecraft.client.util.math.MatrixStack;

public class TestModule extends Module {
    public TestModule() {
        super("Test", "The dupe has been moved over to Dupe:.d 2 btw", ModuleType.HIDDEN);
    }

    @Override public void tick() {

    }

    @Override public void enable() {

    }

    @Override public void disable() {

    }

    @Override public String getContext() {
        return null;
    }

    @Override public void onWorldRender(MatrixStack matrices) {

    }

    @Override public void onHudRender() {
        FontRenderers.normal.drawString(new MatrixStack(), "aa", 0, 0, 0xFFFFFF);
        FontRenderers.normal.drawString(new MatrixStack(), "bb", 0, FontRenderers.normal.getFontHeight(), 0xFFFFFF);
    }

    @Override public void onFastTick() {
        HudRenderer.getInstance().fastTick();
    }
}
