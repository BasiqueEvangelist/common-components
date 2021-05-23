package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.ChunkComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.world.chunk.Chunk;

import java.util.function.Function;

public class CcaChunkComponentRegistry implements ChunkComponentRegistry {
    private final ChunkComponentFactoryRegistry registry;

    public CcaChunkComponentRegistry(ChunkComponentFactoryRegistry registry) {
        this.registry = registry;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory) {
        registry.register(((CcaComponentRef<T>) ref).getWrapped(), c -> new CcaComponent<>(factory.apply(c)));
    }
}
