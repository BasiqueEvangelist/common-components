package me.basiqueevangelist.commoncomponents.test;

import me.basiqueevangelist.commoncomponents.ComponentEvents;
import me.basiqueevangelist.commoncomponents.DeferredComponentRef;
import me.basiqueevangelist.commoncomponents.RespawnCopyStrategy;
import me.shedaniel.architectury.event.events.CommandRegistrationEvent;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Items;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static net.minecraft.server.command.CommandManager.literal;

public class TestMod {
    public static final String MODID = "common-components-testmod";
    public static final DeferredComponentRef<ExampleComponent> EXAMPLE_COMPONENT = new DeferredComponentRef<>(new Identifier(MODID, "example"), ExampleComponent.class, () -> new ExampleComponentImpl(null));

    public static void init() {
        ComponentEvents.ENTITY.register(registry -> {
            registry.registerForPlayers(EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new, RespawnCopyStrategy.INVENTORY);
        });

//        ComponentEvents.ITEM.register(registry -> {
//            registry.registerFor(Items.STONE, EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
//        });

        ComponentEvents.WORLD.register(registry -> {
            registry.register(EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
        });

//        ComponentEvents.CHUNK.register(registry -> {
//            registry.register(EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
//        });

//        ComponentEvents.BLOCK.register(registry -> {
//            registry.registerFor(ChestBlockEntity.class, EXAMPLE_COMPONENT.get(), ExampleComponentImpl::new);
//        });

        CommandRegistrationEvent.EVENT.register((commandDispatcher, registrationEnvironment) -> {
            commandDispatcher.register(literal("synctestcap")
                .executes(ctx -> {
                    ServerPlayerEntity player = ctx.getSource().getPlayer();
                    EXAMPLE_COMPONENT.get().sync(player);

                    EXAMPLE_COMPONENT.get().sync(ctx.getSource().getWorld());

                    return 0;
                }));
        });
    }
}
