package me.basiqueevangelist.commoncomponents.forge.sync;

import io.netty.buffer.Unpooled;
import me.basiqueevangelist.commoncomponents.SyncingComponent;
import me.basiqueevangelist.commoncomponents.forge.CapManagerUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ChunkCapSyncer implements CapSyncer<WorldChunk> {
    @Override
    public Packet<?> toSyncingPacket(WorldChunk provider, Capability<?> cap, SyncingComponent component, ServerPlayerEntity syncingWith) {
        SyncPacket packet = new SyncPacket();
        packet.chunkX = provider.getPos().x;
        packet.chunkZ = provider.getPos().z;
        packet.capName = cap.getName();
        packet.additionalData = new PacketByteBuf(Unpooled.buffer());
        component.toSyncPacket(packet.additionalData, syncingWith);
        return PacketHandlers.CHANNEL.toVanillaPacket(packet, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public List<ServerPlayerEntity> getSyncingPlayers(WorldChunk provider, SyncingComponent component) {
        return ((ServerChunkManager) provider.getWorld().getChunkManager()).threadedAnvilChunkStorage.getPlayersWatchingChunk(provider.getPos(), false).filter(component::syncsWith).collect(Collectors.toList());
    }

    public static void readPacket(SyncPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        ClientWorld world = MinecraftClient.getInstance().world;
        WorldChunk chunk = world.getChunk(packet.chunkX, packet.chunkZ);

        Capability<?> cap = CapManagerUtils.providersMap.get(packet.capName.intern());
        Object capInstance = chunk.getCapability(cap).orElse(null);
        if (capInstance instanceof SyncingComponent) {
            ((SyncingComponent) capInstance).fromSyncPacket(packet.additionalData);
        }

        ctxSupplier.get().setPacketHandled(true);
    }

    public static class SyncPacket {
        public int chunkX, chunkZ;
        public String capName;
        public PacketByteBuf additionalData;

        public void write(PacketByteBuf buf) {
            buf.writeLong(ChunkPos.toLong(chunkX, chunkZ));
            buf.writeString(capName);
            buf.writeBytes(additionalData.copy());
        }

        public static SyncPacket read(PacketByteBuf buf) {
            SyncPacket packet = new SyncPacket();
            long packedPos = buf.readLong();
            packet.chunkX = ChunkPos.getPackedX(packedPos);
            packet.chunkZ = ChunkPos.getPackedZ(packedPos);
            packet.capName = buf.readString();
            packet.additionalData = new PacketByteBuf(buf.readBytes(buf.readableBytes()));
            return packet;
        }
    }
}
