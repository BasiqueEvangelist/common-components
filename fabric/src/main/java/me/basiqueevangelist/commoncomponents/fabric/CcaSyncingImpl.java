package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import me.basiqueevangelist.commoncomponents.SyncingComponent;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public interface CcaSyncingImpl extends AutoSyncedComponent {
    SyncingComponent getWrapped();

    @Override
    default boolean shouldSyncWith(ServerPlayerEntity player) {
        return getWrapped().syncsWith(player);
    }

    @Override
    default void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        getWrapped().toSyncPacket(buf, recipient);
    }

    @Override
    default void applySyncPacket(PacketByteBuf buf) {
        getWrapped().fromSyncPacket(buf);
    }
}
