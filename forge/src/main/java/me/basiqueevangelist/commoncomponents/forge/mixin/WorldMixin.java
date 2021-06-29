package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.component.forge.CapTicking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(World.class)
public class WorldMixin {
    @Shadow @Final public boolean isClient;

    @Inject(method = "tickBlockEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Tickable;tick()V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void tickComponents(CallbackInfo ci, Profiler profiler, Iterator<?> iterator, BlockEntity blockEntity, BlockPos blockPos) {
        if (isClient)
            CapTicking.tickCapsClient(blockEntity);
        else
            CapTicking.tickCapsServer(blockEntity);
    }
}
