package me.basiqueevangelist.commoncomponents.component.fabric.registry;

import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.component.registry.ItemComponentRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.Predicate;

public class CcaItemComponentRegistry implements ItemComponentRegistry {
    private final ItemComponentFactoryRegistry registry;

    public CcaItemComponentRegistry(ItemComponentFactoryRegistry registry) {
        this.registry = registry;
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T extends Component> void registerFor(Item item, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        registry.registerFor(item, ((CcaComponentRef<T>) ref).getWrapped(), stack -> CcaComponent.getWrapperFor(factory.apply(stack)));
    }

    @Override
    public <T extends Component> void registerFor(Identifier itemId, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        registry.registerFor(itemId, ((CcaComponentRef<T>) ref).getWrapped(), stack -> CcaComponent.getWrapperFor(factory.apply(stack)));
    }

    @Override
    public <T extends Component> void registerFor(Predicate<Item> test, ComponentRef<T> ref, Function<ItemStack, ? extends T> factory) {
        registry.registerFor(test, ((CcaComponentRef<T>) ref).getWrapped(), stack -> CcaComponent.getWrapperFor(factory.apply(stack)));
    }
}
