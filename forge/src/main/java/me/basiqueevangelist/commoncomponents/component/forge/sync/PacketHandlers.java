package me.basiqueevangelist.commoncomponents.component.forge.sync;

import me.basiqueevangelist.commoncomponents.CommonComponents;
import net.minecraft.util.Identifier;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public final class PacketHandlers {
    private PacketHandlers() {

    }

    public static String VERSION = "1";
    public static SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(new Identifier(CommonComponents.MOD_ID, "sync"))
        .clientAcceptedVersions(x -> x.equals(VERSION))
        .serverAcceptedVersions(x -> x.equals(VERSION) || x.equals(NetworkRegistry.ABSENT))
        .networkProtocolVersion(() -> VERSION)
        .simpleChannel();

    static {
        CHANNEL.registerMessage(0, EntityCapSyncer.SyncPacket.class, EntityCapSyncer.SyncPacket::write, EntityCapSyncer.SyncPacket::read, EntityCapSyncer::readPacket);
        CHANNEL.registerMessage(1, ChunkCapSyncer.SyncPacket.class, ChunkCapSyncer.SyncPacket::write, ChunkCapSyncer.SyncPacket::read, ChunkCapSyncer::readPacket);
        CHANNEL.registerMessage(2, BlockEntityCapSyncer.SyncPacket.class, BlockEntityCapSyncer.SyncPacket::write, BlockEntityCapSyncer.SyncPacket::read, BlockEntityCapSyncer::readPacket);
        CHANNEL.registerMessage(3, WorldCapSyncer.SyncPacket.class, WorldCapSyncer.SyncPacket::write, WorldCapSyncer.SyncPacket::read, WorldCapSyncer::readPacket);
    }

    public static void init() {
        // Registers the channel.
    }
}
