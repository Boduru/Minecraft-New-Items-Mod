package net.fabricmc.disco.registries;

import net.fabricmc.disco.items.GlowItem;
import net.fabricmc.disco.items.JumperItem;
import net.fabricmc.disco.items.LightningDropperItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.fabricmc.disco.DiscoveryMod.MOD_ID;

public class Items {
    public static final GlowItem GLOW_RING = new GlowItem();
    public static final JumperItem JUMPER = new JumperItem();
    public static final LightningDropperItem LIGHTNING_DROPPER = new LightningDropperItem();

    public static void RegisterItems() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "glow_ring"), GLOW_RING);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "redstone_torch_jumper"), JUMPER);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "lightning_dropper"), LIGHTNING_DROPPER);
    }
}
