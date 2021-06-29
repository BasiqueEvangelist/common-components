package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.component.forge.sync.CapSyncing;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Inject(method = "sendWorldInfo", at = @At("RETURN"))
    private void syncWorldComponents(ServerPlayerEntity player, ServerWorld world, CallbackInfo ci) {
        CapSyncing.syncAllWith(world, player);
    }
}
