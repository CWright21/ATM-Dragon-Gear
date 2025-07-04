package net.cwright21.dragongear.event;

import com.iafenvoy.iceandfire.entity.util.IVillagerFear;
import com.iafenvoy.iceandfire.item.armor.ItemDragonSteelArmor;
import com.iafenvoy.iceandfire.item.armor.ItemScaleArmor;
import com.iafenvoy.iceandfire.item.armor.ItemTrollArmor;
import com.iafenvoy.iceandfire.registry.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.damagesource.DamageContainer;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

import java.util.UUID;
import java.util.function.Predicate;

public class ServerEvents {

	public static final UUID ALEX_UUID = UUID.fromString("71363abe-fd03-49c9-940d-aae8b8209b7c");
    public static final String BOLT_DONT_DESTROY_LOOT = "iceandfire.bolt_skip_loot";
    // FIXME :: No check for shouldFear()?
    private static final Predicate<LivingEntity> VILLAGER_FEAR = entity -> entity instanceof IVillagerFear fear && fear.shouldFear();
    private static final String[] VILLAGE_TYPES = new String[]{"plains", "desert", "snowy", "savanna", "taiga"};
	
    //move to main event bus class
    @SubscribeEvent // on the game event bus
    public static void decreaseArmor(LivingIncomingDamageEvent event) {
        // We only apply this decrease to players and leave zombies etc. unchanged
        if (event.getEntity() instanceof Player) {
            // Add our reduction modifier callback.
        	
        	if(event.getContainer().getSource().is(IafDamageTypes.DRAGON_FIRE_TYPE)) {
        		
        	}
            event.getContainer().addModifier(
                // The reduction to target. See the DamageContainer.Reduction enum for possible values.
                DamageContainer.Reduction.ARMOR,
                // The modification to perform. Gets the damage container and the base reduction as inputs,
                // and outputs the new reduction. Both input and output reductions are floats.
                (container, baseReduction) -> baseReduction * 0.5f
            );
        }
    }
    
	public static float onEntityDamage(LivingEntity entity, DamageSource source, float amount) {
        if (source.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE)) {
            float multi = 1;
            if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ItemTrollArmor)
                multi -= 0.1f;
            if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ItemTrollArmor)
                multi -= 0.3f;
            if (entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ItemTrollArmor)
                multi -= 0.2f;
            if (entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ItemTrollArmor)
                multi -= 0.1f;
            amount *= multi;
        }
        if (source.is(IafDamageTypes.DRAGON_FIRE_TYPE) || source.is(IafDamageTypes.DRAGON_ICE_TYPE) || source.is(IafDamageTypes.DRAGON_LIGHTNING_TYPE)) {
            float multi = 1;
            if (entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ItemScaleArmor ||
                    entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ItemDragonSteelArmor)
                multi -= 0.1f;
            if (entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ItemScaleArmor ||
                    entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ItemDragonSteelArmor)
                multi -= 0.3f;
            if (entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ItemScaleArmor ||
                    entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ItemDragonSteelArmor)
                multi -= 0.2f;
            if (entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ItemScaleArmor ||
                    entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ItemDragonSteelArmor)
                multi -= 0.1f;
            amount *= multi;
        }
        return amount;
    }

	
}
