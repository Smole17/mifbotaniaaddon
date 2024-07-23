package ru.smole.mifbotaniaaddon;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import ru.smole.mifbotaniaaddon.item.BotaniaAddonItems;

import java.util.function.BiConsumer;

public class MIFBotaniaAddon implements ModInitializer {

    public static final String MOD_ID = "mifbotaniaaddon";

    @Override
    public void onInitialize() {
        BotaniaAddonItems.register(bind(Registry.ITEM));
    }

    private static <T> BiConsumer<T, Identifier> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }
}
