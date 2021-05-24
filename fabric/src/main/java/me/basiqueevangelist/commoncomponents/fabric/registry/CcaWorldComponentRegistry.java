package me.basiqueevangelist.commoncomponents.fabric.registry;

import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.registry.WorldComponentRegistry;
import net.minecraft.world.World;

import java.util.function.Function;

public class CcaWorldComponentRegistry implements WorldComponentRegistry {
    private final WorldComponentFactoryRegistry registry;

    public CcaWorldComponentRegistry(WorldComponentFactoryRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<World, T> factory) {
        registry.register(((CcaComponentRef<T>) ref).getWrapped(), w -> CcaComponent.getWrapperFor(factory.apply(w)));
    }
}
