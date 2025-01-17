/*
 * This file is part of the atomic client distribution.
 * Copyright (c) 2021-2021 0x150.
 */

package me.zeroX150.atomic.mixin.game;

import com.mojang.authlib.GameProfile;
import me.zeroX150.atomic.Atomic;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListEntry.class)
public class PlayerListEntryMixin {
    @Shadow
    @Final
    private GameProfile profile;

    @Inject(method = "getCapeTexture", at = @At("HEAD"), cancellable = true)
    public void getCapeTexture(CallbackInfoReturnable<Identifier> cir) {
        GameProfile context = this.profile;
        boolean hasCape = Atomic.capes.containsKey(context.getId());
        if (hasCape) {
            cir.setReturnValue(new Identifier("atomic", "capes/" + Atomic.capes.get(context.getId())));
        }
    }
}
