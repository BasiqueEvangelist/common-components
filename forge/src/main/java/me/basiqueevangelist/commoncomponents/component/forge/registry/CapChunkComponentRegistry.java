package me.basiqueevangelist.commoncomponents.component.forge.registry;

import me.basiqueevangelist.commoncomponents.component.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.component.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.component.registry.ChunkComponentRegistry;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
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
