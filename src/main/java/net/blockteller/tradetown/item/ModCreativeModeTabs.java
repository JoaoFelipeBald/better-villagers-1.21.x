package net.blockteller.tradetown.item;

import net.blockteller.tradetown.TradeTown;
import net.blockteller.tradetown.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB=
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TradeTown.MOD_ID);

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TAB.register("example_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EXAMPLEITEM.get()))
                    .title(Component.translatable("creativetab.tradetown.example_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.EXAMPLEITEM);
                        output.accept(ModBlocks.EXAMPLE_BLOCK);
                        output.accept(ModItems.CUSTOM_ITEM);
                        output.accept(ModBlocks.MAGIC_BLOCK);
                        output.accept(ModItems.FROSTFIRE_ICE);
                        output.accept(ModItems.RADISH);
                        output.accept(ModItems.STARLIGHT_ASHES);

                    }).build());

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB2 = CREATIVE_MODE_TAB.register("example_tab2",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.EXAMPLEITEM.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TradeTown.MOD_ID, "example_tab"))
                    .title(Component.translatable("creativetab.tradetown.example_tab2"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.EXAMPLEITEM);
                        output.accept(ModBlocks.EXAMPLE_BLOCK);
                    }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }

}
