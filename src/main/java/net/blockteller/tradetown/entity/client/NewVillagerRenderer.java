package net.blockteller.tradetown.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.blockteller.tradetown.TradeTown;
import net.blockteller.tradetown.entity.custom.NewVillagerEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class NewVillagerRenderer extends MobRenderer<NewVillagerEntity, NewVillagerModel<NewVillagerEntity>> {
    public NewVillagerRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new NewVillagerModel<>(pContext.bakeLayer(ModModelLayers.NEW_VILLAGER_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(NewVillagerEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(TradeTown.MOD_ID, "textures/entity/newvillager.png");
    }

    @Override
    public void render(NewVillagerEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}