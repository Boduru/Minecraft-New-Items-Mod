package net.fabricmc.disco.items;

import net.fabricmc.disco.utils.Raycast;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class LightningDropperItem extends Item {
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            MinecraftClient client = MinecraftClient.getInstance();
            Vec3d direction = user.getRotationVec(1.0F);
            float tickDelta = client.getTickDelta();

            BlockPos blockPos = Raycast.getBlockInDirection(client, tickDelta, direction);

            if (blockPos != null) {
                spawnLightning(world, blockPos, 10);
            }
        }

        if (world.isClient()) {
            user.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 2.0F, 1.0F);
        }

        return super.use(world, user, hand);
    }

    public void spawnLightning(World world, BlockPos pos, int radius) {
        for (int i = -radius; i < radius; i++) {
            for (int j = -radius; j < radius; j++) {
                for (int k = -radius; k < radius; k++) {
                    if (Math.random() > 0.85) {
                        LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                        lightningEntity.setPosition(pos.getX() + i, pos.getY() + j, pos.getZ() + k);
                        world.spawnEntity(lightningEntity);
                    }
                }
            }
        }
    }

    public LightningDropperItem() {
        super(new Settings().maxCount(1));
    }
}
