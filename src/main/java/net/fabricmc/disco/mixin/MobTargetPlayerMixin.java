package net.fabricmc.disco.mixin;

import net.fabricmc.disco.items.GlowItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.entity.mob.MobEntity")
public class MobTargetPlayerMixin {
    @Inject(method = "Lnet/minecraft/entity/mob/MobEntity;setTarget(Lnet/minecraft/entity/LivingEntity;)V", at = @At("TAIL")) //, cancellable = true)
    private void setTarget(@Nullable LivingEntity target, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null && target != null) {
            PlayerEntity player = client.player;

            if (player != null) {
                if (!player.getMainHandStack().isEmpty()) {
                    Item item = player.getMainHandStack().getItem();

                    if (item instanceof GlowItem glowItem) {
                        MobEntity mob = (MobEntity) (Object) this;

                        if (glowItem.getState() && mob != null)
                            mob.setTarget(null);
                    }
                }
            }
        }
    }
}
