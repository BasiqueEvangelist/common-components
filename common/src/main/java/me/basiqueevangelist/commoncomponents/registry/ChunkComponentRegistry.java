package me.basiqueevangelist.commoncomponents.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.world.chunk.Chunk;

import java.util.function.Function;

public interface ChunkComponentRegistry {
    <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory);
}
