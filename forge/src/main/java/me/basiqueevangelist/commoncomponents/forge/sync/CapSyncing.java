package me.basiqueevangelist.commoncomponents.forge.sync;

import me.basiqueevangelist.commoncomponents.SyncingComponent;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.List;

public final class CapSyncing {
    private CapSyncing() {

    }

    @SuppressWarnings("unchecked")
    private static <T extends ICapabilityProvider> CapSyncer<T> getSyncerFor(T provider) {
        if (provider instanceof Entity) {
            return (CapSyncer<T>) new EntityCapSyncer();
        } else if (provider instanceof WorldChunk) {
            return (CapSyncer<T>) new ChunkCapSyncer();
        } else {
            throw new IllegalStateException("Couldn't find syncer for provider of type " + provider.getClass());
        }
    }

    public static <T extends ICapabilityProvider, Cap> void syncCap(T provider, Capability<Cap> cap) {
        CapSyncer<T> syncer = getSyncerFor(provider);
        Cap inst = provider.getCapability(cap).orElse(null);
        if (!(inst instanceof SyncingComponent)) return;

        List<ServerPlayerEntity> players = syncer.getSyncingPlayers(provider, (SyncingComponent) inst);

        for (ServerPlayerEntity player : players) {
            if (((SyncingComponent) inst).syncsWith(player)) {
                Packet<?> packet = syncer.toSyncingPacket(provider, cap, (SyncingComponent) inst, player);
                player.networkHandler.sendPacket(packet);
            }
        }
    }
}
