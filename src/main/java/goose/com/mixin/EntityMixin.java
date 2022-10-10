package goose.com.mixin;

import goose.com.damage.ModDamage;
import goose.com.tag.ModFluidTags;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin implements Nameable, EntityLike, CommandOutput {

    @Shadow protected boolean firstUpdate;

    @Shadow public abstract boolean isInvulnerable();

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow @Final protected Random random;

    @Shadow protected Object2DoubleMap<TagKey<Fluid>> fluidHeight;

    @Shadow public abstract boolean updateMovementInFluid(TagKey<Fluid> tag, double speed);

    public void killFromVoid() {
        if (this.isInvulnerable()) {
            return;
        }
        if (this.damage(ModDamage.IN_VOID, 4.0f)) {
            this.playSound(SoundEvents.ENTITY_GENERIC_HURT, 0.4f, 2.0f + this.random.nextFloat() + 0.4f);
        }
    }

    @Inject(method = "updateWaterState", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;updateMovementInFluid(Lnet/minecraft/tag/TagKey;D)Z",
        shift = At.Shift.AFTER))
    private void goose$updateWaterState(CallbackInfoReturnable<Boolean> cir) {
        updateMovementInFluid(ModFluidTags.VOID, 0);
    }

    @Unique public boolean bathingInVoid(){
        return !this.firstUpdate && this.fluidHeight.getDouble(ModFluidTags.VOID) > 0.0;
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    public void goose$baseTick(CallbackInfo ci) {
        if (this.bathingInVoid()) {
            this.killFromVoid();
        }
    }
}
