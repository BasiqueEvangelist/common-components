package me.basiqueevangelist.commoncomponents.component.fabric.registry;

import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.component.registry.ChunkComponentRegistry;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import net.minecraft.world.chunk.Chunk;

import java.util.function.Function;

public class CcaChunkComponentRegistry implements ChunkComponentRegistry {
    private final ChunkComponentFactoryRegistry registry;

    public CcaChunkComponentRegistry(ChunkComponentFactoryRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory) {
        registry.register(((CcaComponentRef<T>) ref).getWrapped(), c -> CcaComponent.getWrapperFor(factory.apply(c)));
    }
}
