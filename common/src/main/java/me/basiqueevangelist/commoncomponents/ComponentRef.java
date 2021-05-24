package me.basiqueevangelist.commoncomponents;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public interface ComponentRef<T extends Component> {
    @Nullable T getFromHolder(Object provider);

    Identifier getComponentId();

    Class<T> getComponentClass();

    void sync(Object provider);
}
