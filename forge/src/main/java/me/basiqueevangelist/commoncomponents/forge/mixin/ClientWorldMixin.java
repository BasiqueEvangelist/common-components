package me.basiqueevangelist.commoncomponents.forge.mixin;

import me.basiqueevangelist.commoncomponents.forge.CapTicking;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
    @Inject(method = "tickEntity", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;tick()V", shift = At.Shift.AFTER))
    private void tickEntityClientComponents(Entity entity, CallbackInfo ci) {
        CapTicking.tickCapsClient(entity);
    }

    @Inject(method = "tick", at = @At(value = "RETURN"))
    private void tickClientComponents(CallbackInfo ci) {
        CapTicking.tickCapsClient((ClientWorld)(Object) this);
    }
}
