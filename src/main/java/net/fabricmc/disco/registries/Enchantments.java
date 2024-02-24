package net.fabricmc.disco.registries;

import net.fabricmc.disco.enchantments.ExplosionEnchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.fabricmc.disco.DiscoveryMod.MOD_ID;

public class Enchantments {
    public static final ExplosionEnchantment EXPLOSION = new ExplosionEnchantment();
    public static void RegisterEnchantments() {
        Registry.register(Registries.ENCHANTMENT, new Identifier(MOD_ID, "explosion_enchantment"), EXPLOSION);
    }
}
