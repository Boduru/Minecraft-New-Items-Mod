package net.fabricmc.disco.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class GlowItem extends Item {
    private boolean state = false;

    public GlowItem() {
        super(new FabricItemSettings().maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (!world.isClient()) {
            // Toggle the state
            state = !state;

            if (state) {
                // Add effects to the player
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100000, 1, false, false));
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 100000, 1, false, false));
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 100000, 5, true, true));
                // Make player invisible
                playerEntity.setInvisible(true);
            }
            else {
                // Remove effects from the player
                playerEntity.removeStatusEffect(StatusEffects.NAUSEA);
                playerEntity.removeStatusEffect(StatusEffects.DARKNESS);
                playerEntity.removeStatusEffect(StatusEffects.INVISIBILITY);
                // Make player visible
                playerEntity.setInvisible(false);
            }
        }

        if (world.isClient()) {
            // Play a sound
            playerEntity.playSound(SoundEvents.BLOCK_SCULK_CATALYST_BLOOM, 2.0F, 1.0F);
        }

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    public boolean getState() {
        return state;
    }
}
