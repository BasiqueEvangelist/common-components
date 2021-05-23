package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.EntityComponentRegistry;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapabilityEntityComponentRegistry implements EntityComponentRegistry {
    private final AttachCapabilitiesEvent<Entity> event;

    public CapabilityEntityComponentRegistry(AttachCapabilitiesEvent<Entity> event) {
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Component, E extends Entity> void registerFor(Class<E> target, ComponentRef<T> ref, Function<E, ? extends T> factory) {
        if (target.isInstance(event.getObject()))
            event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply((E) event.getObject())));
    }
}
