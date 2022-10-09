package goose.com.entity;

import goose.com.tag.ModFluidTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class ModEntity extends Entity {
    public ModEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

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

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    @Override
    public Packet<?> createSpawnPacket() {
        return null;
    }
}
