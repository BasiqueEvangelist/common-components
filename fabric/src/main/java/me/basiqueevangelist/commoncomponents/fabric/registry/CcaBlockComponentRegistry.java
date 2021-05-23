package me.basiqueevangelist.commoncomponents.fabric.registry;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.registry.BlockComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
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
        registry.registerFor(target, ((CcaComponentRef<T>) ref).getWrapped(), be -> new CcaComponent<>(factory.apply(be)));
    }
}
