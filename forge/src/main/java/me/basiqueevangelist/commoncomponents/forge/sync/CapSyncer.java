package me.basiqueevangelist.commoncomponents.forge.sync;

import me.basiqueevangelist.commoncomponents.SyncingComponent;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.List;

public interface CapSyncer<T extends ICapabilityProvider> {
    Packet<?> toSyncingPacket(T provider, Capability<?> cap, SyncingComponent component, ServerPlayerEntity syncingWith);

    List<ServerPlayerEntity> getSyncingPlayers(T provider, SyncingComponent component);
}
