package me.basiqueevangelist.commoncomponents.fabric;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import me.basiqueevangelist.commoncomponents.ComponentEvents;

public class ComponentEventHooksImpl implements EntityComponentInitializer, ItemComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        ComponentEvents.ENTITY.invoker().registerEntityComponents(new CcaEntityComponentRegistry(registry));
    }

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        ComponentEvents.ITEM.invoker().registerItemComponents(new CcaItemComponentRegistry(registry));
    }
}
