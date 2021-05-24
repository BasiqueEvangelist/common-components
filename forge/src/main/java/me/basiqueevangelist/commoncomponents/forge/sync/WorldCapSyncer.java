package me.basiqueevangelist.commoncomponents.forge.sync;

import io.netty.buffer.Unpooled;
import me.basiqueevangelist.commoncomponents.SyncingComponent;
import me.basiqueevangelist.commoncomponents.forge.CapManagerUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WorldCapSyncer implements CapSyncer<ServerWorld> {
    @Override
    public Packet<?> toSyncingPacket(ServerWorld provider, Capability<?> cap, SyncingComponent component, ServerPlayerEntity syncingWith) {
        SyncPacket packet = new SyncPacket();
        packet.capName = cap.getName();
        packet.additionalData = new PacketByteBuf(Unpooled.buffer());
        component.toSyncPacket(packet.additionalData, syncingWith);
        return PacketHandlers.CHANNEL.toVanillaPacket(packet, NetworkDirection.PLAY_TO_CLIENT);
    }

    @Override
    public List<ServerPlayerEntity> getSyncingPlayers(ServerWorld provider, SyncingComponent component) {
        return provider.getPlayers().stream().filter(component::syncsWith).collect(Collectors.toList());
    }

    public static void readPacket(SyncPacket packet, Supplier<NetworkEvent.Context> ctxSupplier) {
        ClientWorld world = MinecraftClient.getInstance().world;
        Capability<?> cap = CapManagerUtils.getCapability(packet.capName);
        Object capInstance = world.getCapability(cap).orElse(null);
        if (capInstance instanceof SyncingComponent) {
            ((SyncingComponent) capInstance).fromSyncPacket(packet.additionalData);
        }

        ctxSupplier.get().setPacketHandled(true);
    }

    public static class SyncPacket {
        public String capName;
        public PacketByteBuf additionalData;

        public void write(PacketByteBuf buf) {
            buf.writeString(capName);
            buf.writeBytes(additionalData.copy());
        }

        public static SyncPacket read(PacketByteBuf buf) {
            SyncPacket packet = new SyncPacket();
            packet.capName = buf.readString();
            packet.additionalData = new PacketByteBuf(buf.readBytes(buf.readableBytes()));
            return packet;
        }
    }
}
