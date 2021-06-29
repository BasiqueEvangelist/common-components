package me.basiqueevangelist.commoncomponents.component;

import me.shedaniel.architectury.annotations.ExpectPlatform;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class Components {
    @ExpectPlatform
    static <T extends Component> void registerDeferred(DeferredComponentRef<T> ref, Identifier componentId, Class<T> componentInterface, Supplier<T> defaultFactory) {
        throw new RuntimeException();
    }

    @ExpectPlatform
    public static <T extends Component> ComponentRef<T> get(Identifier componentId, Class<T> componentInterface) {
        throw new RuntimeException();
    }
}
