package me.basiqueevangelist.commoncomponents;

import me.shedaniel.architectury.event.Event;
import me.shedaniel.architectury.event.EventFactory;

public class ComponentEvents {
    public static final Event<EntityComponentAttacher> ENTITY = EventFactory.createLoop();
    public static final Event<ItemComponentAttacher> ITEM = EventFactory.createLoop();
    public static final Event<WorldComponentAttacher> WORLD = EventFactory.createLoop();

    public interface EntityComponentAttacher {
        void registerEntityComponents(EntityComponentRegistry registry);
    }

    public interface ItemComponentAttacher {
        void registerItemComponents(ItemComponentRegistry registry);
    }

    public interface WorldComponentAttacher {
        void registerWorldComponents(WorldComponentRegistry registry);
    }
}
