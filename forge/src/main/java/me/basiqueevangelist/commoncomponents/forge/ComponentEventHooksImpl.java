package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.ComponentEvents;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ComponentEventHooksImpl {
    private ComponentEventHooksImpl() {

    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(ComponentEventHooksImpl.class);
    }

    @SubscribeEvent
    protected static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        ComponentEvents.ENTITY.invoker().registerEntityComponents(new CapabilityEntityComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachBlockEntityCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        ComponentEvents.BLOCK.invoker().registerBlockComponents(new CapabilityBlockComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachItemStackCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ComponentEvents.ITEM.invoker().registerItemComponents(new CapabilityItemComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachWorldCapabilities(AttachCapabilitiesEvent<World> event) {
        ComponentEvents.WORLD.invoker().registerWorldComponents(new CapabilityWorldComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachChunkCapabilities(AttachCapabilitiesEvent<WorldChunk> event) {
        ComponentEvents.CHUNK.invoker().registerChunkComponents(new CapabilityChunkComponentRegistry(event));
    }
}
