package net.blockteller.tradetown.event;

import net.blockteller.tradetown.TradeTown;
import net.blockteller.tradetown.entity.ModEntities;
import net.blockteller.tradetown.entity.custom.NewVillagerEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = TradeTown.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.NEW_VILLAGER.get(), NewVillagerEntity.createAttributes().build());
    }
}