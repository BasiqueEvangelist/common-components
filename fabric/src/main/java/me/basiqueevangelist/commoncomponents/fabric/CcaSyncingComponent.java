package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.SyncingComponent;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class CcaSyncingComponent<T extends Component & SyncingComponent> extends CcaComponent<T> implements AutoSyncedComponent {
    public CcaSyncingComponent(T wrapped) {
        super(wrapped);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return wrapped.syncsWith(player);
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        wrapped.toSyncPacket(buf, recipient);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        wrapped.fromSyncPacket(buf);
    }
}
