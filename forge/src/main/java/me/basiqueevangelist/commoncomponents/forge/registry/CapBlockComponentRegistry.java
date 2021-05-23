package me.basiqueevangelist.commoncomponents.forge.registry;

import me.basiqueevangelist.commoncomponents.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.registry.BlockComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.block.entity.BlockEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapBlockComponentRegistry implements BlockComponentRegistry {
    private final AttachCapabilitiesEvent<BlockEntity> event;

    public CapBlockComponentRegistry(AttachCapabilitiesEvent<BlockEntity> event) {
        this.event = event;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Component, B extends BlockEntity> void registerFor(Class<B> target, ComponentRef<T> ref, Function<B, T> factory) {
        if (target.isInstance(event.getObject()))
            event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply((B) event.getObject())));
    }
}
