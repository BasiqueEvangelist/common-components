package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.forge.sync.CapSyncing;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ThreadedAnvilChunkStorage.class)
public class ThreadedAnvilChunkStorageMixin {
    @Inject(method = "sendChunkDataPackets", at = @At("RETURN"))
    private void syncChunkAndBEComponents(ServerPlayerEntity player, Packet<?>[] packets, WorldChunk chunk, CallbackInfo ci) {
        CapSyncing.syncAllWith(chunk, player);
        for (BlockEntity be : chunk.getBlockEntities().values()) {
            CapSyncing.syncAllWith(be, player);
        }
    }
}
