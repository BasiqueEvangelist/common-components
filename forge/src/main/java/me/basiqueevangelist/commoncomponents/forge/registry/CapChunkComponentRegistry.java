package me.basiqueevangelist.commoncomponents.forge.registry;

import me.basiqueevangelist.commoncomponents.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.registry.ChunkComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapChunkComponentRegistry implements ChunkComponentRegistry {
    private final AttachCapabilitiesEvent<WorldChunk> event;

    public CapChunkComponentRegistry(AttachCapabilitiesEvent<WorldChunk> event) {
        this.event = event;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory) {
        event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply(event.getObject())));
    }
}
