package net.cwright21.dragongear.event;

import net.cwright21.dragongear.util.Const;

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
import net.silentchaos512.gear.util.TraitHelper;

import java.util.UUID;
import java.util.function.Predicate;

public class ServerEvents {
    
	public static float onEntityDamage(LivingEntity entity, DamageSource source, float amount) {
		
		//reduce projectile damage by 5% per level of Thick Hide
        if (source.is(net.minecraft.tags.DamageTypeTags.IS_PROJECTILE)) {
            int helmetThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            int chestThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            int legsThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            int feetThickHide = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.THICK_HIDE);
            amount *= 1f - (helmetThickHide + chestThickHide + legsThickHide + feetThickHide) * 0.05f;
        }
        
      //reduce dragon breath damage by 5% per level of Dragon Protection
        if (source.is(IafDamageTypes.DRAGON_FIRE_TYPE) || source.is(IafDamageTypes.DRAGON_ICE_TYPE) || source.is(IafDamageTypes.DRAGON_LIGHTNING_TYPE)) {
        	int helmetDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            int chestDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            int legsDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            int feetDragProt = TraitHelper.getTraitLevel(entity.getItemBySlot(EquipmentSlot.HEAD), Const.Traits.DRAGON_PROTECTION);
            amount *= 1f - (helmetDragProt + chestDragProt + legsDragProt + feetDragProt) * 0.05f;
        }
        return amount;
    }

	
}
