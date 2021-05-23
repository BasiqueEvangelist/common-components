package me.basiqueevangelist.commoncomponents.forge.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.registry.WorldComponentRegistry;
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
