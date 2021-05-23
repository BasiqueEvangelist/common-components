package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.BlockComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapabilityBlockComponentRegistry implements BlockComponentRegistry {
    private final AttachCapabilitiesEvent<BlockEntity> event;

    public CapabilityBlockComponentRegistry(AttachCapabilitiesEvent<BlockEntity> event) {
        this.event = event;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Component, B extends BlockEntity> void registerFor(Class<B> target, ComponentRef<T> ref, Function<B, T> factory) {
        if (target.isInstance(event.getObject()))
            event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply((B) event.getObject())));
    }
}
