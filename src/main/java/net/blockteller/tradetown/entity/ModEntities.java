package net.blockteller.tradetown.entity;

import net.blockteller.tradetown.TradeTown;
import net.blockteller.tradetown.entity.custom.NewVillagerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.core.registries.Registries; // Import the Registries class

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, TradeTown.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<NewVillagerEntity>> NEW_VILLAGER =
            ENTITY_TYPES.register("newvillager", () -> EntityType.Builder.of(NewVillagerEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 1.0f).build("newvillager"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
