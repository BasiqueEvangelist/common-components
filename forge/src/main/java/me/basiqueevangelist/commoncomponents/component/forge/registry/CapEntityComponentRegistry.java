package me.basiqueevangelist.commoncomponents.component.forge.registry;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.RespawnCopyStrategy;
import me.basiqueevangelist.commoncomponents.component.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.component.forge.CapCopying;
import me.basiqueevangelist.commoncomponents.component.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.component.registry.EntityComponentRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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

    @Override
    public <T extends Component, Impl extends T> void registerForPlayers(ComponentRef<T> ref, Function<PlayerEntity, Impl> factory, RespawnCopyStrategy<? super Impl> respawnStrategy) {
        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply((PlayerEntity) event.getObject())));

        CapCopying.setCapabilityStrategy(((CapComponentRef<T>) ref).getCapability(), respawnStrategy);
    }
}
