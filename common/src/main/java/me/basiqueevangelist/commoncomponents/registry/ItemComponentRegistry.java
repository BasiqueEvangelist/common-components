package me.basiqueevangelist.commoncomponents.registry;

import me.basiqueevangelist.commoncomponents.Component;
import me.basiqueevangelist.commoncomponents.ComponentRef;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.Predicate;

public interface ItemComponentRegistry {
    <T extends Component> void registerFor(Item item, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory);

    <T extends Component> void registerFor(Identifier itemId, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory);

    <T extends Component> void registerFor(Predicate<Item> test, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory);
}
