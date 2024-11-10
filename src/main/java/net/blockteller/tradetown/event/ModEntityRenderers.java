package net.blockteller.tradetown.event;

import net.blockteller.tradetown.TradeTown;
import net.blockteller.tradetown.entity.ModEntities;
import net.blockteller.tradetown.entity.client.ModModelLayers;
import net.blockteller.tradetown.entity.client.NewVillagerModel;
import net.blockteller.tradetown.entity.client.NewVillagerRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = TradeTown.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        // Register the renderer for the NewVillagerEntity
        event.registerEntityRenderer(ModEntities.NEW_VILLAGER.get(), NewVillagerRenderer::new);
    }


    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        // Register the model layer for NewVillagerModel
        event.registerLayerDefinition(ModModelLayers.NEW_VILLAGER_LAYER, NewVillagerModel::createBodyLayer);
    }

}
