package me.basiqueevangelist.commoncomponents.component.registry;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import net.minecraft.world.chunk.Chunk;

import java.util.function.Function;

public interface ChunkComponentRegistry {
    <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory);
}
