package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.forge.CapTicking;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "tickEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V", shift = At.Shift.AFTER))
    private void tickServerComponents(Entity entity, CallbackInfo ci) {
        CapTicking.tickCapsServer(entity);
    }

    @Inject(method = "tickChunk", at = @At("RETURN"))
    private void tickChunkServerComponents(WorldChunk chunk, int randomTickSpeed, CallbackInfo ci) {
        CapTicking.tickCapsServer(chunk);
    }
}
