package me.basiqueevangelist.commoncomponents;

import net.minecraft.world.chunk.Chunk;

import java.util.function.Function;

public interface ChunkComponentRegistry {
    <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory);
}
