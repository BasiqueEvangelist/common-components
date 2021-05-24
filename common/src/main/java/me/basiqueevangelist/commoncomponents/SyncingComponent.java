package me.basiqueevangelist.commoncomponents;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public interface SyncingComponent extends Component {
    default boolean syncsWith(ServerPlayerEntity player) { return true; }

    default void toSyncPacket(PacketByteBuf buf, ServerPlayerEntity syncingWith) {
        CompoundTag tag = new CompoundTag();
        toTag(tag);
        buf.writeCompoundTag(tag);
    }

    default void fromSyncPacket(PacketByteBuf buf) {
        fromTag(buf.readCompoundTag());
    }
}
