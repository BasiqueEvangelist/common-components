package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.ChunkComponentRegistry;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;

public class CapabilityChunkComponentRegistry implements ChunkComponentRegistry {
    private final AttachCapabilitiesEvent<WorldChunk> event;

    public CapabilityChunkComponentRegistry(AttachCapabilitiesEvent<WorldChunk> event) {
        this.event = event;
    }

    @Override
    public <T extends Component> void register(ComponentRef<T> ref, Function<Chunk, T> factory) {
        event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply(event.getObject())));
    }
}
