package net.blockteller.tradetown.entity.custom;

import net.blockteller.tradetown.menu.CustomMerchantMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;

import net.blockteller.tradetown.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.OptionalInt;

public class NewVillagerEntity extends PathfinderMob implements Merchant {
    public NewVillagerEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        offers.add(new MerchantOffer(
                new ItemCost(Items.COOKED_BEEF, 1), // Cost 1 emerald
                new ItemStack(Items.COOKED_BEEF, 5), // Gives 5 cooked beef
                10, // Max uses
                2, // Villager experience
                0.05F // Price multiplier
        ));
    }


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private boolean isTrading;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private MerchantOffers offers = new MerchantOffers();

    @Override
    public MerchantOffers getOffers() {
        // Return the 'offers' that was populated in the constructor
        return offers;
    }


    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new TemptGoal(this, 1.2D, Ingredient.of(Items.COOKED_BEEF), false));


    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 2f);
    }



    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.RAVAGER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.DOLPHIN_DEATH;
    }



    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        player.sendSystemMessage(Component.literal(String.valueOf(offers.isEmpty())));
        if (!this.level().isClientSide && hand == InteractionHand.MAIN_HAND && this.isAlive()) {
            if (!this.isTrading) {
                this.startTrading(player);
                player.awardStat(Stats.TALKED_TO_VILLAGER);
                return InteractionResult.SUCCESS;
            } else {
                player.sendSystemMessage(Component.literal("The trading menu is already open."));
                return InteractionResult.CONSUME;
            }
        }
        return super.mobInteract(player, hand);
    }



    private void startTrading(Player player) {
        if (!this.isTrading) {
            this.isTrading = true;
            this.tradingPlayer = player;

            player.sendSystemMessage(Component.literal("Opening trading menu with " + this.getDisplayName().getString()));
            this.openTradingScreen(player, this.getDisplayName(), 1);
        } else {
            player.sendSystemMessage(Component.literal("Menu is already open"));
        }
    }
    @Override
    public void openTradingScreen(Player player, Component displayName, int level) {
        OptionalInt optionalint = player.openMenu(new SimpleMenuProvider((p_45298_, p_45299_, p_45300_) -> {
            return new CustomMerchantMenu(p_45298_, p_45299_, this);
        }, displayName));
        if (optionalint.isPresent()) {
            MerchantOffers merchantoffers = this.getOffers();
            if (!merchantoffers.isEmpty()) {
                player.sendMerchantOffers(optionalint.getAsInt(), merchantoffers, level, this.getVillagerXp(), this.showProgressBar(), this.canRestock());
            }
        }

    }

    @Nullable
    private Player tradingPlayer;

    @Override
    public void setTradingPlayer(@Nullable Player player) {
        this.tradingPlayer = player;
        this.isTrading = player != null; // Set isTrading based on whether a player is currently trading

        if (player == null) {
            stopTrading(); // Ends trading if the player is null
        }
    }

    private void stopTrading() {
        this.isTrading = false;
        this.tradingPlayer = null;
        System.out.println("Trade session ended.");
    }



    @Nullable
    @Override
    public Player getTradingPlayer() {
        return tradingPlayer;  // Return the current trading player
    }

    @Override
    public void overrideOffers(MerchantOffers merchantOffers) {

    }

    @Override
    public void notifyTrade(MerchantOffer merchantOffer) {

    }

    @Override
    public void notifyTradeUpdated(ItemStack itemStack) {

    }

    @Override
    public int getVillagerXp() {
        return 0;
    }

    @Override
    public void overrideXp(int i) {

    }

    @Override
    public boolean showProgressBar() {
        return false;
    }

    @Override
    public SoundEvent getNotifyTradeSound() {
        return null;
    }

    @Override
    public boolean isClientSide() {
        return false;
    }
}