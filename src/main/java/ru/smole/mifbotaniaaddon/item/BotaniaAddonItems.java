package ru.smole.mifbotaniaaddon.item;

import lombok.experimental.UtilityClass;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import ru.smole.mifbotaniaaddon.MIFBotaniaAddon;
import ru.smole.mifbotaniaaddon.item.impl.InfusedManaPowderItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@UtilityClass
public class BotaniaAddonItems {

    private final Map<Identifier, Item> ALL = new LinkedHashMap<>();
    public final Item INFUSED_MANA_POWDER_ITEM = make(new Identifier(MIFBotaniaAddon.MOD_ID, "infused_mana_powder"), new InfusedManaPowderItem(defaultBuilder().rarity(Rarity.RARE)));

    public void registerItems(BiConsumer<Item, Identifier> r) {
        for (var e : ALL.entrySet()) {
            r.accept(e.getValue(), e.getKey());
        }
    }

    public static Item.Settings defaultBuilder() {
        return XplatAbstractions.INSTANCE.defaultItemBuilder();
    }

    private <T extends Item> T make(Identifier id, T item) {
        var old = ALL.put(id, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate id " + id);
        }
        return item;
    }
}
