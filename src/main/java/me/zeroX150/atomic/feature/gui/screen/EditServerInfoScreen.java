/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.feature.gui.screen;

import me.zeroX150.atomic.Atomic;
import me.zeroX150.atomic.helper.font.FontRenderers;
import me.zeroX150.atomic.helper.util.Utils;
import me.zeroX150.atomic.mixin.network.IMultiplayerServerListPingerAccessor;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class EditServerInfoScreen extends Screen {
    final ServerInfo si;
    final Screen parent;
    TextFieldWidget motd;
    TextFieldWidget pcount;

    public EditServerInfoScreen(ServerInfo serverToEdit, Screen parent) {
        super(Text.of(""));
        this.si = serverToEdit;
        this.parent = parent;

    }

    @Override
    protected void init() {
        int fw = 200;
        motd = new TextFieldWidget(textRenderer, width / 2 - fw / 2, height / 2 - 25, fw, 20, Text.of("SPECIAL:Server MOTD"));
        motd.setMaxLength(65535);
        motd.setText(si.label.getString().replaceAll("§", "&").replaceAll("\n", "\\\\n"));
        pcount = new TextFieldWidget(textRenderer, width / 2 - fw / 2, height / 2 + 6, fw, 20, Text.of("SPECIAL:Player count"));
        pcount.setMaxLength(65535);
        pcount.setText(si.playerCountLabel.getString());
        fw = 100;
        ButtonWidget cancel = new ButtonWidget(width / 2 - fw - 3, height - 25, fw, 20, Text.of("Cancel"), button -> Atomic.client.setScreen(parent));
        ButtonWidget save = new ButtonWidget(width / 2 + 3, height - 25, fw, 20, Text.of("Save"), button -> {
            if (pcount.getText().split("/").length == 2) {
                String[] v = pcount.getText().split("/");
                String v1 = v[0];
                String v2 = v[1];
                int i1 = Utils.Math.tryParseInt(v1, -1);
                int i2 = Utils.Math.tryParseInt(v2, -1);
                if (i1 < 0 || i2 < 0) {
                    pcount.setEditableColor(0xFF5555);
                    return;
                }
                pcount.setEditableColor(0xFFFFFF);
                si.label = Text.of(motd.getText().replaceAll("&", "§").replaceAll("\\\\n", "\n"));
                si.playerCountLabel = IMultiplayerServerListPingerAccessor.createPlayerCountText(i1, i2);
                Atomic.client.setScreen(parent);
            } else pcount.setEditableColor(0xFF5555);
        });
        addDrawableChild(motd);
        addDrawableChild(pcount);
        addDrawableChild(save);
        addDrawableChild(cancel);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        FontRenderers.title.drawString(matrices, "Server Editor", 5, 5, 0xFFFFFF);
        List<String> texts = new ArrayList<>();
        texts.add("How to use");
        texts.add("Use & color codes for styling");
        texts.add("Use \"\\n\"s for newlines");
        texts.add("Click \"Save\" to apply changes");
        texts.add("Click \"Cancel\" to abort");
        texts.add("Client side only :sadge:");
        texts.add("");
        texts.add("Syntax for player count: players/max players");
        texts.add("Examples: 1/2, 3/4, 5/10, 69/420, 420/69");
        int yOff = FontRenderers.title.getFontHeight() + 1;
        for (String text : texts) {
            FontRenderers.mono.drawString(matrices, text, 5, yOff, 0xFFFFFF);
            yOff += FontRenderers.mono.getFontHeight();
        }
        FontRenderers.normal.drawString(matrices, motd.getText().isEmpty() ? "" : "Server MOTD", width / 2f - 100, height / 2f - 35, 0xFFFFFF);
        FontRenderers.normal.drawString(matrices, pcount.getText().isEmpty() ? "" : "Player count", width / 2f - 100, height / 2f - 4, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
