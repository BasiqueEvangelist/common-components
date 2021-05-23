package me.basiqueevangelist.commoncomponents.forge;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import me.basiqueevangelist.commoncomponents.ItemComponentRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import java.util.function.Function;
import java.util.function.Predicate;

public class CapabilityItemComponentRegistry implements ItemComponentRegistry {
    private final AttachCapabilitiesEvent<ItemStack> event;

    public CapabilityItemComponentRegistry(AttachCapabilitiesEvent<ItemStack> event) {
        this.event = event;
    }

    @Override
    public <T extends Component> void registerFor(Item item, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        if (event.getObject().getItem() == item)
            event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply(event.getObject())));
    }

    @Override
    public <T extends Component> void registerFor(Identifier itemId, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        if (event.getObject().getItem().getRegistryName() == itemId)
            event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply(event.getObject())));
    }

    @Override
    public <T extends Component> void registerFor(Predicate<Item> test, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        if (test.test(event.getObject().getItem()))
            event.addCapability(ref.getComponentId(), new ComponentCapabilityProvider<>((CapabilityComponentRef<T>) ref, factory.apply(event.getObject())));
    }
}
