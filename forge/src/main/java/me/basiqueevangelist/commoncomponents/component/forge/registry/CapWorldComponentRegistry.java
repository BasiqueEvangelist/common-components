package me.basiqueevangelist.commoncomponents.component.forge.registry;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.component.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.component.registry.WorldComponentRegistry;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapWorldComponentRegistry implements WorldComponentRegistry {
    private final AttachCapabilitiesEvent<World> event;

    public CapWorldComponentRegistry(AttachCapabilitiesEvent<World> event) {
        this.event = event;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory) {
        event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply(event.getObject())));
    }
}
