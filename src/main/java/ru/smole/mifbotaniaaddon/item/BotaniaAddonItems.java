package ru.smole.mifbotaniaaddon.item;

import lombok.experimental.UtilityClass;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import ru.smole.mifbotaniaaddon.MIFBotaniaAddon;
import ru.smole.mifbotaniaaddon.item.impl.ManaGemItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@UtilityClass
public class BotaniaAddonItems {

    private final Map<Identifier, Item> ALL = new LinkedHashMap<>();
    public final Item MANA_GEM_ITEM = make(new Identifier(MIFBotaniaAddon.MOD_ID, "mana_gem"), new ManaGemItem(defaultBuilder().rarity(Rarity.RARE)));

    public void register(BiConsumer<Item, Identifier> r) {
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
