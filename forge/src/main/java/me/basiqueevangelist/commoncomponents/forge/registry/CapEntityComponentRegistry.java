package me.basiqueevangelist.commoncomponents.forge.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.registry.EntityComponentRegistry;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapEntityComponentRegistry implements EntityComponentRegistry {
    private final AttachCapabilitiesEvent<Entity> event;

    public CapEntityComponentRegistry(AttachCapabilitiesEvent<Entity> event) {
        this.event = event;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Component, E extends Entity> void registerFor(Class<E> target, ComponentRef<T> ref, Function<E, ? extends T> factory) {
        if (target.isInstance(event.getObject()))
            event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply((E) event.getObject())));
    }
}
