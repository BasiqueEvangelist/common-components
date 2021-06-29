package me.basiqueevangelist.commoncomponents.component.forge;

import me.basiqueevangelist.commoncomponents.component.ComponentEvents;
import me.basiqueevangelist.commoncomponents.component.forge.registry.*;
import me.basiqueevangelist.commoncomponents.component.forge.sync.CapSyncing;
import me.shedaniel.architectury.event.events.TickEvent;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public final class ComponentEventHooksImpl {
    private ComponentEventHooksImpl() {

    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(ComponentEventHooksImpl.class);
        TickEvent.SERVER_WORLD_POST.register(ComponentEventHooksImpl::onServerWorldEndTick);
    }

    @SubscribeEvent
    protected static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        ComponentEvents.ENTITY.invoker().registerEntityComponents(new CapEntityComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachBlockEntityCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        ComponentEvents.BLOCK.invoker().registerBlockComponents(new CapBlockComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachItemStackCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ComponentEvents.ITEM.invoker().registerItemComponents(new CapItemComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachWorldCapabilities(AttachCapabilitiesEvent<World> event) {
        ComponentEvents.WORLD.invoker().registerWorldComponents(new CapWorldComponentRegistry(event));
    }

    @SubscribeEvent
    protected static void attachChunkCapabilities(AttachCapabilitiesEvent<WorldChunk> event) {
        ComponentEvents.CHUNK.invoker().registerChunkComponents(new CapChunkComponentRegistry(event));
    }

    private static void onServerWorldEndTick(ServerWorld world) {
        CapTicking.tickCapsServer(world);
    }

    @SubscribeEvent
    protected static void onStartTrackingEntity(PlayerEvent.StartTracking event) {
        CapSyncing.syncAllWith(event.getTarget(), (ServerPlayerEntity) event.getPlayer());
    }

    @SubscribeEvent
    protected static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        CapSyncing.syncAllWith(event.getPlayer(), (ServerPlayerEntity) event.getPlayer());
    }

    @SubscribeEvent
    protected static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        CapSyncing.syncAllWith(event.getPlayer(), (ServerPlayerEntity) event.getPlayer());
    }

    @SubscribeEvent
    protected static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        CapSyncing.syncAllWith(event.getPlayer(), (ServerPlayerEntity) event.getPlayer());
    }
}
