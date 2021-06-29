package me.basiqueevangelist.commoncomponents.component.forge.registry;

import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.forge.CapComponentRef;
import me.basiqueevangelist.commoncomponents.component.forge.ComponentCapProvider;
import me.basiqueevangelist.commoncomponents.component.registry.ItemComponentRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;
import java.util.function.Predicate;

public class CapItemComponentRegistry implements ItemComponentRegistry {
    private final AttachCapabilitiesEvent<ItemStack> event;

    public CapItemComponentRegistry(AttachCapabilitiesEvent<ItemStack> event) {
        this.event = event;
    }

    @Override
    public <T extends Component> void registerFor(Item item, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        if (event.getObject().getItem() == item)
            event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply(event.getObject())));
    }

    @Override
    public <T extends Component> void registerFor(Identifier itemId, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        if (event.getObject().getItem().getRegistryName() == itemId)
            event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply(event.getObject())));
    }

    @Override
    public <T extends Component> void registerFor(Predicate<Item> test, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        if (test.test(event.getObject().getItem()))
            event.addCapability(ref.getComponentId(), new ComponentCapProvider<>((CapComponentRef<T>) ref, factory.apply(event.getObject())));
    }
}
