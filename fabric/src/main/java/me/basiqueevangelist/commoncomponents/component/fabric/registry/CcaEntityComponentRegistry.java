package me.basiqueevangelist.commoncomponents.component.fabric.registry;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import me.basiqueevangelist.commoncomponents.component.Component;
import me.basiqueevangelist.commoncomponents.component.ComponentRef;
import me.basiqueevangelist.commoncomponents.component.RespawnCopyStrategy;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponent;
import me.basiqueevangelist.commoncomponents.component.fabric.CcaComponentRef;
import me.basiqueevangelist.commoncomponents.component.registry.EntityComponentRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Function;

public class CcaEntityComponentRegistry implements EntityComponentRegistry {
    private final EntityComponentFactoryRegistry ecfr;

    public CcaEntityComponentRegistry(EntityComponentFactoryRegistry ecfr) {
        this.ecfr = ecfr;
    }

    @Override
    public <T extends Component, E extends Entity> void registerFor(Class<E> target, ComponentRef<T> ref, Function<E, ? extends T> factory) {
        ecfr.registerFor(target, ((CcaComponentRef<T>) ref).getWrapped(), entity -> CcaComponent.getWrapperFor(factory.apply(entity)));
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Component, Impl extends T> void registerForPlayers(ComponentRef<T> ref, Function<PlayerEntity, Impl> factory, RespawnCopyStrategy<? super Impl> respawnStrategy) {
        ecfr.registerForPlayers(((CcaComponentRef<T>) ref).getWrapped(), player -> CcaComponent.getWrapperFor(factory.apply(player)), (from, to, lossless, inventory) -> {
            respawnStrategy.copy(((CcaComponent<Impl>) from).getWrapped(), ((CcaComponent<Impl>) to).getWrapped(), lossless, inventory);
        });
    }
}
