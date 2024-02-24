package net.fabricmc.disco.enchantments;

import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.ThornsEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class ExplosionEnchantment extends ThornsEnchantment {
    public ExplosionEnchantment() {
        super(Rarity.RARE, EquipmentSlot.CHEST);
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if (user != null && attacker != null) {
            if (user instanceof PlayerEntity client) {
                if (client.world != null) {
                    client.world.createExplosion(attacker, attacker.getX(), attacker.getY(), attacker.getZ(), 8.0F, World.ExplosionSourceType.TNT);
                    attacker.damage(user.getDamageSources().thorns(user), 30.F);
                }
            }
        }
    }

    @Override
    public int getMinPower(int level) {
        return 30;
    }

    @Override
    public int getMaxPower(int level) {
        return 60;
    }
}
