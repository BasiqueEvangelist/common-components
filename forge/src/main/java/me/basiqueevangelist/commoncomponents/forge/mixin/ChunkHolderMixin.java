package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.forge.sync.CapSyncing;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkHolder.class)
public class ChunkHolderMixin {
    @Inject(method = "flushUpdates", at = @At(value = "FIELD", target = "Lnet/minecraft/server/world/ChunkHolder;pendingBlockUpdates:Z", opcode = Opcodes.PUTFIELD))
    private void sendChunkUpdates(WorldChunk chunk, CallbackInfo ci) {
        CapSyncing.syncAll(chunk);
    }

    @Inject(method = "sendBlockEntityUpdatePacket", at = @At(value = "RETURN"))
    private void syncBlockEntityComponents(World world, BlockPos pos, CallbackInfo ci) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be != null)
            CapSyncing.syncAll(be);
    }
}
