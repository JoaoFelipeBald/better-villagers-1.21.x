package net.blockteller.tradetown.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;

public class CustomMerchantMenu extends MerchantMenu {
    private final Merchant trader;
    public CustomMerchantMenu(int containerId, Inventory playerInventory, Merchant trader) {
        super(containerId, playerInventory, trader);
        this.trader = trader;
    }
    @Override
    public void setOffers(MerchantOffers offers) {
        this.trader.overrideOffers(offers);
    }
}
