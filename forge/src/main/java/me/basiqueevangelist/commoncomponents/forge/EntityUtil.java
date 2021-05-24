package me.basiqueevangelist.commoncomponents.forge;

import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ThreadedAnvilChunkStorage;

import java.util.Set;

public final class EntityUtil {
    private EntityUtil() {

    }

    public static Set<ServerPlayerEntity> getPlayersTracking(Entity e) {
        ThreadedAnvilChunkStorage tacs = ((ServerChunkManager) e.getEntityWorld().getChunkManager()).threadedAnvilChunkStorage;
        return tacs.entityTrackers.get(e.getEntityId()).playersTracking;
    }
}
