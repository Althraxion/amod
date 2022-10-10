package goose.com.damage;

import net.minecraft.entity.damage.DamageSource;

public class ModDamage extends DamageSource {
    public static final DamageSource IN_VOID = (new ModDamage("inVoid")).setBypassesArmor();
    public ModDamage(String name) {
        super(name);
    }
}

