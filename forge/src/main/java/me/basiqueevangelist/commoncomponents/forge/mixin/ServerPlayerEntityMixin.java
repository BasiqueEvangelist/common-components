package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.forge.sync.CapSyncing;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(method = "sendBlockEntityUpdate", at = @At("RETURN"))
    private void syncBlockEntityComponents(BlockEntity be, CallbackInfo ci) {
        CapSyncing.syncAllWith(be, (ServerPlayerEntity)(Object) this);
    }
}
