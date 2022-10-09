package goose.com.mixin;

import goose.com.tag.ModFluidTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;

@Mixin(Entity.class)
public abstract class EntityMix implements Nameable, EntityLike, CommandOutput {

    @Shadow public abstract void kill();

    @Unique public boolean bathingInVoid(){
        return !this.firstUpdate && this.fluidHeight.getDouble(ModFluidTags.VOID) > 0.0;
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    public void goose$baseTick(CallbackInfo ci) {
        if (this.bathingInVoid()) {
            this.kill();
        }
    }
}
