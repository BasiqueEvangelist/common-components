package me.basiqueevangelist.commoncomponents.component.forge.sync;

import io.netty.buffer.Unpooled;
import me.basiqueevangelist.commoncomponents.component.SyncingComponent;
import me.basiqueevangelist.commoncomponents.component.forge.CapManagerUtils;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlockEntityCapSyncer implements CapSyncer<BlockEntity> {
    @Override
    public Packet<?> toSyncingPacket(BlockEntity provider, Capability<?> cap, SyncingComponent component, ServerPlayerEntity syncingWith) {
        SyncPacket packet = new SyncPacket();
        packet.x = provider.getPos().getX();
        packet.y = provider.getPos().getY();
        packet.z = provider.getPos().getZ();
        packet.capName = cap.getName();
        packet.additionalData = new PacketByteBuf(Unpooled.buffer());
        component.toSyncPacket(packet.additionalData, syncingWith);
        return PacketHandlers.CHANNEL.toVanillaPacket(packet, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public List<ServerPlayerEntity> getSyncingPlayers(BlockEntity provider, SyncingComponent component) {
        return ((ServerChunkManager) provider.getWorld().getChunkManager()).threadedAnvilChunkStorage.getPlayersWatchingChunk(new ChunkPos(provider.getPos()), false).filter(component::syncsWith).collect(Collectors.toList());
    }

    public static void readPacket(SyncPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        ClientWorld world = MinecraftClient.getInstance().world;
        BlockEntity be = world.getBlockEntity(new BlockPos(packet.x, packet.y, packet.z));
        if (be == null) return;

        Capability<?> cap = CapManagerUtils.getCapability(packet.capName);
        Object capInstance = be.getCapability(cap).orElse(null);
        if (capInstance instanceof SyncingComponent) {
            ((SyncingComponent) capInstance).fromSyncPacket(packet.additionalData);
        }

        ctxSupplier.get().setPacketHandled(true);
    }

    public static class SyncPacket {
        public int x, y, z;
        public String capName;
        public PacketByteBuf additionalData;

        public void write(PacketByteBuf buf) {
            buf.writeLong(BlockPos.asLong(x, y, z));
            buf.writeString(capName);
            buf.writeBytes(additionalData.copy());
        }

        public static SyncPacket read(PacketByteBuf buf) {
            SyncPacket packet = new SyncPacket();
            long packedPos = buf.readLong();
            packet.x = BlockPos.unpackLongX(packedPos);
            packet.y = BlockPos.unpackLongY(packedPos);
            packet.z = BlockPos.unpackLongZ(packedPos);
            packet.capName = buf.readString();
            packet.additionalData = new PacketByteBuf(buf.readBytes(buf.readableBytes()));
            return packet;
        }
    }
}
