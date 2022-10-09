package goose.com.entity;

import goose.com.tag.ModFluidTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Nameable;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityLike;

public abstract class ModEntity extends Entity implements Nameable, EntityLike, CommandOutput {

    public ModEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public boolean bathingInVoid(){
        return !this.firstUpdate && this.fluidHeight.getDouble(ModFluidTags.VOID) > 0.0;
    }

    @Override
    public void baseTick() {
        if (this.bathingInVoid()) {
            this.kill();
        }
    }
}
