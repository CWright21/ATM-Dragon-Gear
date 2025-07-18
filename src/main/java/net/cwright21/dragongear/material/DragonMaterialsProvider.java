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
import com.iafenvoy.iceandfire.data.TrollType;
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
        
    	// lightning (more magic damage)
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.LIGHTNING_DRAGONSTEEL.getMaterial())
                .crafting(IafItems.DRAGONSTEEL_LIGHTNING_INGOT.get(), MaterialCategories.METAL, MaterialCategories.ENDGAME )
                .display(getDisplayName("Lightning Dragonsteel"),0x3e2547, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(8000, 4, 20, 16, 1.1f)
                .mainStatsHarvest(12)
                .mainStatsArmor(7, 12, 9, 6, 6, 10)
                .mainStatsMelee(20, 12, 0.1f)
                .trait(PartTypes.MAIN, Const.Traits.HEAVY, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 2)

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
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 2)
                //TODO: add ice damage trait

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
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 2)
                //TODO: add fire damage trait

        );
        
    }
    
    private void addDragonScales(Collection<MaterialBuilder<?>> ret) {
    	Map<IceAndFireMaterials, Item> lightningDragonScales = Map.of(
    			IceAndFireMaterials.DRAGONSCALES_ELECTRICBLUE, IafItems.DRAGONSCALES_ELECTRIC.get(),
    			IceAndFireMaterials.DRAGONSCALES_COPPER, IafItems.DRAGONSCALES_COPPER.get(),
    			IceAndFireMaterials.DRAGONSCALES_AMETHYST, IafItems.DRAGONSCALES_AMETHYST.get(),
    			IceAndFireMaterials.DRAGONSCALES_BLACK, IafItems.DRAGONSCALES_BLACK.get()
    			);
    	
    	for(IceAndFireMaterials material : lightningDragonScales.keySet()) {
    		ret.add(MaterialBuilder.simple(material.getMaterial())
                    .crafting(lightningDragonScales.get(material), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                    .display(getDisplayName("Lightning Dragon Scale"),0xce4646, TextureType.LOW_CONTRAST)
                    //main
                    .mainStatsCommon(500, 4, 12, 8, 0.9f)
                    .mainStatsArmor(5, 9, 7, 5, 1.5f, 20)
                    .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                    .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 1)
            );
    	}
    	
    	Map<IceAndFireMaterials, Item> iceDragonScales = Map.of(
    			IceAndFireMaterials.DRAGONSCALES_BLUE, IafItems.DRAGONSCALES_BLUE.get(),
    			IceAndFireMaterials.DRAGONSCALES_WHITE, IafItems.DRAGONSCALES_WHITE.get(),
    			IceAndFireMaterials.DRAGONSCALES_SAPPHIRE, IafItems.DRAGONSCALES_SAPPHIRE.get(),
    			IceAndFireMaterials.DRAGONSCALES_SILVER, IafItems.DRAGONSCALES_SILVER.get()
    			);
    	
    	for(IceAndFireMaterials material : iceDragonScales.keySet()) {
    		ret.add(MaterialBuilder.simple(material.getMaterial())
                    .crafting(iceDragonScales.get(material), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                    .display(getDisplayName("Ice Dragon Scale"),0xce4646, TextureType.LOW_CONTRAST)
                    //main
                    .mainStatsCommon(500, 4, 12, 8, 0.9f)
                    .mainStatsArmor(5, 9, 7, 5, 2, 15)
                    .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                    .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 1)
            );
    	}
        
    	Map<IceAndFireMaterials, Item> fireDragonScales = Map.of(
    			IceAndFireMaterials.DRAGONSCALES_RED, IafItems.DRAGONSCALES_RED.get(),
    			IceAndFireMaterials.DRAGONSCALES_EMERALD, IafItems.DRAGONSCALES_GREEN.get(),
    			IceAndFireMaterials.DRAGONSCALES_BRONZE, IafItems.DRAGONSCALES_BRONZE.get(),
    			IceAndFireMaterials.DRAGONSCALES_GREY, IafItems.DRAGONSCALES_GRAY.get()
    			);
    	
    	for(IceAndFireMaterials material : fireDragonScales.keySet()) {
    		ret.add(MaterialBuilder.simple(material.getMaterial())
                    .crafting(fireDragonScales.get(material), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                    .display(getDisplayName("Fire Dragon Scale"),0xce4646, TextureType.LOW_CONTRAST)
                    //main
                    .mainStatsCommon(500, 4, 12, 8, 0.9f)
                    .mainStatsArmor(5, 9, 7, 5, 2, 15)
                    .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                    .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.DRAGON_PROTECTION, 1)
            );
    	}
    
    }
    
    private void addTrollHides(Collection<MaterialBuilder<?>> ret) {
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.TROLL_LEATHER_FROST.getMaterial())
                .crafting(TrollType.FROST.leather.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Frost Troll Leather"),0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(300, 4, 12, 8, 0.9f)
                .mainStatsArmor(7, 12, 9, 6, 1, 10)
                .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
                .trait(PartTypes.GRIP, Const.Traits.STURDY, 1)
        );
    	
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.TROLL_LEATHER_FOREST.getMaterial())
                .crafting(TrollType.FOREST.leather.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Forest Troll Leather"),0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(300, 4, 12, 8, 0.9f)
                .mainStatsArmor(7, 12, 9, 6, 1, 10)
                .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
                .trait(PartTypes.GRIP, Const.Traits.STURDY, 1)
        );
    	
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.TROLL_LEATHER_MOUNTAIN.getMaterial())
                .crafting(TrollType.MOUNTAIN.leather.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Mountain Troll Leather"),0xce4646, TextureType.LOW_CONTRAST)
                //main
                .mainStatsCommon(300, 4, 12, 8, 0.9f)
                .mainStatsArmor(3, 7, 5, 2, 1, 10)
                .trait(PartTypes.MAIN, Const.Traits.FLEXIBLE, 1)
                .trait(PartTypes.MAIN, net.cwright21.dragongear.util.Const.Traits.THICK_HIDE, 1)
                .trait(PartTypes.GRIP, Const.Traits.STURDY, 1)
        );
    	
    }
    
    private void addShinyScales(Collection<MaterialBuilder<?>> ret) {
        ret.add(MaterialBuilder.simple(IceAndFireMaterials.SHINY_SCALES.getMaterial())
                .crafting(IafItems.SHINY_SCALES.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Shiny Scale"),0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.LINING, GearProperties.ENCHANTMENT_VALUE, 2f)
                .trait(PartTypes.LINING, Const.Traits.AQUATIC, 1)
        );
    }
    
    private void addDragonBloodCoatings(Collection<MaterialBuilder<?>> ret) {
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.FIRE_DRAGON_BLOOD.getMaterial())
                .crafting(IafItems.FIRE_DRAGON_BLOOD.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Fire Dragon Blood"),0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.COATING, GearProperties.MAGIC_DAMAGE, 8f, NumberProperty.Operation.ADD)
                
            	//TODO: Fire Blood effect : ignite and knockback? via custom trait
        );
    	
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.ICE_DRAGON_BLOOD.getMaterial())
                .crafting(IafItems.ICE_DRAGON_BLOOD.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Ice Dragon Blood"),0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.COATING, GearProperties.MAGIC_DAMAGE, 8f, NumberProperty.Operation.ADD)
                
                //TODO: Ice Blood effect : freeze?
        );
    	
    	ret.add(MaterialBuilder.simple(IceAndFireMaterials.LIGHTNING_DRAGON_BLOOD.getMaterial())
                .crafting(IafItems.LIGHTNING_DRAGON_BLOOD.get(), MaterialCategories.ORGANIC, MaterialCategories.ADVANCED )
                .display(getDisplayName("Lightning Dragon Blood"),0xce4646, TextureType.LOW_CONTRAST)
                
                .stat(PartTypes.COATING, GearProperties.MAGIC_DAMAGE, 8f, NumberProperty.Operation.ADD)
                
                //TODO: Lightning Blood effect : lightning?
        );
    	
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