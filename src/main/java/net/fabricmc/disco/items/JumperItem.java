package net.fabricmc.disco.items;

import net.fabricmc.disco.utils.Raycast;
import net.minecraft.client.MinecraftClient;
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

public class JumperItem extends Item {
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            MinecraftClient client = MinecraftClient.getInstance();
            Vec3d direction = user.getRotationVec(1.0F);
            float tickDelta = client.getTickDelta();

            BlockPos blockPos = Raycast.getBlockInDirection(client, tickDelta, direction);

            if (blockPos != null) {
                user.sendMessage(Text.literal(blockPos.toString()));
                user.teleport(blockPos.getX(), blockPos.getY() + user.getHeight(), blockPos.getZ());
            }
        }

        if (world.isClient()) {
            user.playSound(SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, 2.0F, 1.0F);
        }

        return super.use(world, user, hand);
    }

    public JumperItem() {
        super(new Settings().maxCount(1));
    }
}
