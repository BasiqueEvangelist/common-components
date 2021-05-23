package me.basiqueevangelist.commoncomponents.test;

import me.basiqueevangelist.commoncomponents.ComponentEvents;
import me.basiqueevangelist.commoncomponents.DeferredComponentRef;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class TestMod {
    public static final String MODID = "common-components-testmod";
    public static final DeferredComponentRef<ExampleComponent> EXAMPLE_COMPONENT = new DeferredComponentRef<>(new Identifier(MODID, "example"), ExampleComponent.class, () -> new ExampleComponentImpl(null));

    public static void init() {
        ComponentEvents.ENTITY.register(registry -> {
            registry.registerFor(LivingEntity.class, EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
        });

        ComponentEvents.ITEM.register(registry -> {
            registry.registerFor(Items.STONE, EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
        });

        ComponentEvents.WORLD.register(registry -> {
            registry.register(EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
        });
    }
}
