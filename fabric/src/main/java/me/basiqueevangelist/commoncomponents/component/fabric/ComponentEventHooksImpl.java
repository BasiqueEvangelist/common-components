package me.basiqueevangelist.commoncomponents.component.fabric;

import dev.onyxstudios.cca.api.v3.block.BlockComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.block.BlockComponentInitializer;
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.chunk.ChunkComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import me.basiqueevangelist.commoncomponents.component.ComponentEvents;
import me.basiqueevangelist.commoncomponents.component.fabric.registry.*;

public class ComponentEventHooksImpl implements EntityComponentInitializer, ItemComponentInitializer, WorldComponentInitializer, ChunkComponentInitializer, BlockComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        ComponentEvents.ENTITY.invoker().registerEntityComponents(new CcaEntityComponentRegistry(registry));
    }

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        ComponentEvents.ITEM.invoker().registerItemComponents(new CcaItemComponentRegistry(registry));
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        ComponentEvents.WORLD.invoker().registerWorldComponents(new CcaWorldComponentRegistry(registry));
    }

    @Override
    public void registerChunkComponentFactories(ChunkComponentFactoryRegistry registry) {
        ComponentEvents.CHUNK.invoker().registerChunkComponents(new CcaChunkComponentRegistry(registry));
    }

    @SuppressWarnings("UnstableApiUsage")
    @Override
    public void registerBlockComponentFactories(BlockComponentFactoryRegistry registry) {
        ComponentEvents.BLOCK.invoker().registerBlockComponents(new CcaBlockComponentRegistry(registry));
    }
}
