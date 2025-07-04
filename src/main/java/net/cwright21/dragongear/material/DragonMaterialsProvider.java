package net.cwright21.dragongear.material;

import net.cwright21.dragongear.DragonGear;
import net.cwright21.dragongear.core.IceAndFireMaterials;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.silentchaos512.gear.api.data.material.MaterialBuilder;
import net.silentchaos512.gear.api.data.material.MaterialsProviderBase;
import net.silentchaos512.gear.api.material.MaterialCraftingData;
import net.silentchaos512.gear.api.material.TextureType;
import net.silentchaos512.gear.api.property.NumberProperty;
import net.silentchaos512.gear.api.util.PartGearKey;
import net.silentchaos512.gear.core.BuiltinMaterials;
import net.silentchaos512.gear.gear.material.MaterialCategories;
import net.silentchaos512.gear.setup.gear.GearProperties;
import net.silentchaos512.gear.setup.gear.GearTypes;
import net.silentchaos512.gear.setup.gear.PartTypes;
import net.silentchaos512.gear.util.Const;
import net.silentchaos512.lib.util.Color;
import com.mojang.brigadier.Message;

import com.iafenvoy.iceandfire.registry.IafItems;


//import dev.architectury.registry.registries.RegistrySupplier;

import java.util.*;
public class DragonMaterialsProvider extends MaterialsProviderBase {
    public DragonMaterialsProvider(DataGenerator generator) {
        super(generator, DragonGear.NAMESPACE);
    }

	@Override
	protected Collection<MaterialBuilder<?>> getMaterials() {
		Collection<MaterialBuilder<?>> ret = new ArrayList<>();

        //example(ret); //example showing Iron and Bone materials
		addDragonBone(ret);
		addDragonSteel(ret);
		addDragonScales(ret);
		addTrollHides(ret);
		addShinyScales(ret);
		addDragonBloodCoatings(ret);
		
        return ret;
	}
    
    @SuppressWarnings("unused")
	private void example(Collection<MaterialBuilder<?>> ret) {
    	// Iron
        ret.add(MaterialBuilder.builtin(BuiltinMaterials.IRON)
                .craftingWithCommonRod(Tags.Items.INGOTS_IRON, MaterialCategories.METAL, MaterialCategories.INTERMEDIATE)
                .displayWithDefaultName(Color.VALUE_WHITE, TextureType.HIGH_CONTRAST)
                //main
                .mainStatsCommon(250, 15, 14, 20, 0.7f)
                .mainStatsHarvest(6)
                .mainStatsMelee(2, 1, 0.0f)
                .stat(PartGearKey.ofMain(GearTypes.AXE), GearProperties.ATTACK_SPEED, -0.1f)
                .stat(PartGearKey.ofMain(GearTypes.HOE), GearProperties.ATTACK_SPEED, 0f)
                .mainStatsRanged(1, 0.1f)
                .mainStatsProjectile(1.0f, 1.1f)
                .mainStatsArmor(2, 6, 5, 2, 0, 6) //15
                .trait(PartTypes.MAIN, Const.Traits.MALLEABLE, 3)
                //rod
                .trait(PartTypes.ROD, Const.Traits.FLEXIBLE, 2)
                //tip
                .stat(PartTypes.TIP, GearProperties.DURABILITY, 128, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.ARMOR_DURABILITY, 4, NumberProperty.Operation.ADD)
                .harvestTierBuiltin(PartTypes.TIP)
                .stat(PartTypes.TIP, GearProperties.HARVEST_SPEED, 1, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.ATTACK_DAMAGE, 1, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.DRAW_SPEED, 0.2f, NumberProperty.Operation.ADD)
                .stat(PartTypes.TIP, GearProperties.RARITY, 8, NumberProperty.Operation.ADD)
                .trait(PartTypes.TIP, Const.Traits.MALLEABLE, 2)
                );
        
     // Bone
        ret.add(MaterialBuilder.builtin(BuiltinMaterials.BONE)
                .crafting(new MaterialCraftingData(
                        Ingredient.of(Items.BONE_BLOCK),
                        List.of(MaterialCategories.ORGANIC, MaterialCategories.BASIC),
                        List.of(),
                        Map.of(PartTypes.ROD.get(), Ingredient.of(Items.BONE)),
                        true
                ))
                .displayWithDefaultName(0xFCFBED, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(108, 4, 5, 8, 0.9f)
                .mainStatsHarvest(4)
                .mainStatsMelee(2, 1, 0.1f)
                .mainStatsRanged(1f, 0f, 0.9f, 1f)
                .mainStatsArmor(1, 2, 1, 1, 0, 1) //5
                .trait(PartTypes.MAIN, Const.Traits.CHIPPING, 2)
                //rod
                .stat(PartTypes.ROD, GearProperties.ATTACK_DAMAGE, 0.2f, NumberProperty.Operation.MULTIPLY_TOTAL)
                .trait(PartTypes.ROD, Const.Traits.FLEXIBLE, 2)
        );
    }
    
    private void addDragonBone(Collection<MaterialBuilder<?>> ret) {
        
    	// Dragon Bone
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.DRAGON_BONE.getMaterial())
                .crafting(IafItems.DRAGON_BONE.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Dragonbone"),0xFCFBED, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(1660, 0, 10, 8, 1f)
                .mainStatsHarvest(9)
                .mainStatsMelee(4, 1, 0.1f)
                .mainStatsRanged(1.1f, 0f, 0.9f, 1f)
                .trait(PartTypes.MAIN, Const.Traits.CHIPPING, 2)
                //rod
                .trait(PartTypes.ROD, Const.Traits.FLEXIBLE, 2)
        );
        // Witherbone
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.WITHER_BONE.getMaterial())
                .crafting(new MaterialCraftingData(
                        Ingredient.of(),
                        List.of(MaterialCategories.ORGANIC, MaterialCategories.ADVANCED),
                        List.of(),
                        Map.of(PartTypes.ROD.get(), Ingredient.of(IafItems.WITHERBONE.get())),
                        true
                ))
                .display(getDisplayName("Witherbone"),0x0e0c0f, TextureType.LOW_CONTRAST)
                //rod
                .stat(PartTypes.ROD, GearProperties.ATTACK_DAMAGE, 0.2f, NumberProperty.Operation.MULTIPLY_TOTAL)
                .trait(PartTypes.ROD, Const.Traits.BRITTLE, 2)
        );
    
    }
    
    private void addDragonSteel(Collection<MaterialBuilder<?>> ret) {
        
    	// lightning
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.LIGHTNING_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_LIGHTNING_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .display(getDisplayName("Lightning Dragonsteel"),0x3e2547, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 16, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(20, 12, 0.1f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                // TODO: Add Reduced Breath damage 2

        );
        
        // ice
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.ICE_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_ICE_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .display(getDisplayName("Ice Dragonsteel"),0x73c9e2, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 16, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(24, 8, 0.1f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                // TODO: Add Reduced Breath damage 2

        );
        
        // fire
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.FIRE_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_FIRE_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .display(getDisplayName("Fire Dragonsteel"),0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 13, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(24, 8, 0.1f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                // TODO: Add Reduced Breath damage 2

        );
        
    }
    
    private void addDragonScales(Collection<MaterialBuilder<?>> ret) {
        
    	// lightning
        // put lightning colors here Map<IceAndFireMaterials.ITEM_NAME, IafItems.ITEM_NAME.get()>
        
        // ice
        // put ice colors here Map<IceAndFireMaterials.ITEM_NAME, IafItems.ITEM_NAME.get()>
        
        // fire
    	// put fire colors here Map<IceAndFireMaterials.ITEM_NAME, IafItems.ITEM_NAME.get()>
    	
    	//for each color item_name
    	//build material
    	
    	// TODO: lightning specific stats
    	// TODO: Ice specific stats
    	// TODO: Fire specific stats 
    	// TODO: Add Reduced Breath damage 1
    
    }
    
    private void addTrollHides(Collection<MaterialBuilder<?>> ret) {
    	//TODO: Frost troll hide
    	
    	//TODO: Forest troll hide
    	
    	//TODO: Mountain troll hide
    	
    }
    
    private void addShinyScales(Collection<MaterialBuilder<?>> ret) {
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.SHINY_SCALES.getMaterial())
                .crafting(IafItems.SHINY_SCALES.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Shiny Scale"),0xce4646, TextureType.LOW_CONTRAST)
                //main
                .stat(PartTypes.LINING, GearProperties.ENCHANTMENT_VALUE, 2f)
                .trait(PartTypes.LINING, Const.Traits.AQUATIC, 1)
        );
    }
    
    private void addDragonBloodCoatings(Collection<MaterialBuilder<?>> ret) {
    	//TODO: all add 4 magic damage
    	
    	//TODO: Fire Blood effect : ignite and knockback?
    	//TODO: Ice Blood effect : freeze?
    	//TODO: Lightning Blood effect : lightning?
    }
    
    //this is the more correct way, but this seems to also include [] in the name
    @SuppressWarnings("unused")
	private Component getDisplayName(Item item) {
    	return item.getDefaultInstance().getDisplayName();
    }
    
    //this is a weird and bad way to get an item name, especially since it wont translate....
    private Component getDisplayName(String text) {
    	Message m = new CustomMessage(text);
    	return ComponentUtils.fromMessage(m);
    }
    
    //I do not know how to remove the brackets from the item name so this is what I came up with
    // Generate a new component with the text we want
    // Using message since message is a simple interface that has a Component method
    public class CustomMessage implements Message{
    	private String text;
    	
    	public CustomMessage(String text) {
    		super();
    		this.text = text;
    	}
    	
		@Override
		public String getString() {
			return this.text;
		}
    	
    }
    
}