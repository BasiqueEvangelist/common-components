package me.basiqueevangelist.commoncomponents.component.fabric.registry;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.component.registry.BlockComponentRegistry;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import net.minecraft.block.entity.BlockEntity;

import java.util.function.Function;

@SuppressWarnings("UnstableApiUsage")
public class CcaBlockComponentRegistry implements BlockComponentRegistry {
    private final BlockComponentFactoryRegistry registry;

    public CcaBlockComponentRegistry(BlockComponentFactoryRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T extends Component, B extends BlockEntity> void registerFor(Class<B> target, ComponentRef<T> ref, Function<B, T> factory) {
        registry.registerFor(target, ((CcaComponentRef<T>) ref).getWrapped(), be -> CcaComponent.getWrapperFor(factory.apply(be)));
    }
}
