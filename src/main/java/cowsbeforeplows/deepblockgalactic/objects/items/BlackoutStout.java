package cowsbeforeplows.deepblockgalactic.objects.items;

import cowsbeforeplows.deepblockgalactic.init.ItemInit;
import cowsbeforeplows.deepblockgalactic.util.DimTeleporter;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class BlackoutStout extends Item {
	
	public BlackoutStout(Properties properties) {
		super(properties);
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
		PlayerEntity playerentity = entityLiving instanceof PlayerEntity ? (PlayerEntity)entityLiving : null;
		
		if(playerentity instanceof ServerPlayerEntity) {
			CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayerEntity)playerentity, stack);
			playerentity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 10, true, false));
			playerentity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 100, 10, true, false));
			ItemHandlerHelper.giveItemToPlayer(playerentity, new ItemStack(ItemInit.EMPTY_MUG.get()));
		}
		
		if (playerentity != null) {
	         playerentity.addStat(Stats.ITEM_USED.get(this));
	         if (!playerentity.abilities.isCreativeMode) {
	            stack.shrink(1);
	            playerentity.inventory.addItemStackToInventory(new ItemStack(ItemInit.EMPTY_MUG.get()));
	         }
		}
		
		return super.onItemUseFinish(stack, worldIn, entityLiving);
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
		
	
	}
	
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		playerIn.setActiveHand(handIn);
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
	
	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.DRINK;
	}
	
	public int getUseDuration(ItemStack stack) {
		return 64;
	}
	


}
