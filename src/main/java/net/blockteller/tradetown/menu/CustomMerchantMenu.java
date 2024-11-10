package net.blockteller.tradetown.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.MerchantContainer;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffers;

public class CustomMerchantMenu extends AbstractContainerMenu {
    private final Merchant trader;
    private final MerchantContainer tradeContainer;
    public CustomMerchantMenu(int containerId, Inventory playerInventory, Merchant trader) {
        super(MenuType.MERCHANT, containerId);
        this.trader = trader;
        this.tradeContainer = new MerchantContainer(trader);
    }


    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
