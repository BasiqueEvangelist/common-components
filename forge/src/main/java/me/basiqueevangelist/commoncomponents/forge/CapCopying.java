package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.RespawnCopyStrategy;
import net.minecraft.world.GameRules;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public final class CapCopying {
    private CapCopying() {

    }

    public static void init() {
        MinecraftForge.EVENT_BUS.register(CapCopying.class);
    }

    private static final Map<Capability<?>, RespawnCopyStrategy<?>> copyStrategies = new HashMap<>();

    public static void setCapabilityStrategy(Capability<?> cap, RespawnCopyStrategy<?> strategy) {
        copyStrategies.put(cap, strategy);
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    protected static void onPlayerClone(PlayerEvent.Clone event) {
        for (Map.Entry<Capability<?>, RespawnCopyStrategy<?>> strategies : copyStrategies.entrySet()) {
            tryStrategyFor(event, (Capability<Component>) strategies.getKey(), (RespawnCopyStrategy<Component>) strategies.getValue());
        }
    }

    private static <T extends Component> void tryStrategyFor(PlayerEvent.Clone event, Capability<T> cap, RespawnCopyStrategy<T> strategy) {
        boolean inv = event.getPlayer().getEntityWorld().getGameRules().getBoolean(GameRules.KEEP_INVENTORY) || event.getOriginal().isSpectator();

        T origInstance = event.getOriginal().getCapability(cap).orElse(null);
        T newInstance = event.getOriginal().getCapability(cap).orElse(null);

        if (origInstance != null && newInstance != null) {
            strategy.copy(origInstance, newInstance, !event.isWasDeath(), inv);
        }
    }
}
