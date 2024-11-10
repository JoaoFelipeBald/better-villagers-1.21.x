package net.blockteller.tradetown.entity.client;

import net.blockteller.tradetown.TradeTown;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation NEW_VILLAGER_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TradeTown.MOD_ID, "newvillagerlayer"), "main");
}