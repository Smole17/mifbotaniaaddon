package ru.smole.mifbotaniaaddon.item.impl;

import lombok.val;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import ru.smole.mifbotaniaaddon.item.BotaniaAddonItems;
import vazkii.botania.api.item.ManaDissolvable;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.helper.EntityHelper;
import vazkii.botania.network.EffectType;
import vazkii.botania.network.clientbound.BotaniaEffectPacket;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;

public class InfusedManaPowderItem extends Item implements ManaDissolvable {

    public InfusedManaPowderItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return super.hasGlint(stack) || stack.isOf(BotaniaAddonItems.INFUSED_MANA_POWDER_ITEM);
    }

    @Override
    public void onDissolveTick(ManaPool pool, ItemEntity item) {
        if (pool.isFull() || pool.getCurrentMana() == 0) return;

        val pos = pool.getManaReceiverPos();

        if (!item.world.isClient) {
            pool.receiveMana((int) (pool.getMaxMana() * .1));
            EntityHelper.shrinkItem(item);
            XplatAbstractions.INSTANCE.sendToTracking(item, new BotaniaEffectPacket(EffectType.BLACK_LOTUS_DISSOLVE, pos.getX(), pos.getY() + 0.5, pos.getZ()));
        }

        item.playSound(BotaniaSounds.blackLotus, 1F, .1F);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("mifbotaniaaddon.infused_mana_powder_desc").formatted(Formatting.GRAY));
    }
}