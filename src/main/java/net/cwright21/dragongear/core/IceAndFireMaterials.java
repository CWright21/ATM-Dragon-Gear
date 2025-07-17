package net.cwright21.dragongear.core;

import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.silentchaos512.gear.SilentGear;
import net.silentchaos512.gear.api.material.Material;
import net.silentchaos512.gear.api.property.HarvestTier;
import net.silentchaos512.gear.api.util.DataResource;
import net.silentchaos512.gear.setup.SgTags;

import javax.annotation.Nullable;

import com.iafenvoy.iceandfire.IceAndFire;

import java.util.List;
import java.util.function.Function;

public enum IceAndFireMaterials {

	DRAGON_BONE("dragonbone", "3", Tiers.DIAMOND),
	WITHER_BONE("witherbone", "3", Tiers.DIAMOND),
	ICE_DRAGONSTEEL("dragonsteel_ice_ingot", "4", Tiers.NETHERITE),
	FIRE_DRAGONSTEEL("dragonsteel_fire_ingot", "4", Tiers.NETHERITE),
	LIGHTNING_DRAGONSTEEL("dragonsteel_lightning_ingot", "4", Tiers.NETHERITE),
	SHINY_SCALES("shiny_scales", "2", Tiers.IRON),
	FIRE_DRAGON_BLOOD("fire_dragon_blood", "3", Tiers.DIAMOND),
	ICE_DRAGON_BLOOD("ice_dragon_blood", "3", Tiers.DIAMOND),
	LIGHTNING_DRAGON_BLOOD("lightning_dragon_blood", "3", Tiers.DIAMOND),
	TROLL_LEATHER_FOREST("troll_leather_forest", "2", Tiers.IRON),
	TROLL_LEATHER_FROST("troll_leather_frost", "2", Tiers.IRON),
	TROLL_LEATHER_MOUNTAIN("troll_leather_mountain", "2", Tiers.IRON),
	DRAGONSCALES_RED("dragonscales_red", "3", Tiers.DIAMOND),
	DRAGONSCALES_EMERALD("dragonscales_emerald", "3", Tiers.DIAMOND),
	DRAGONSCALES_BRONZE("dragonscales_bronze", "3", Tiers.DIAMOND),
	DRAGONSCALES_GREY("dragonscales_grey", "3", Tiers.DIAMOND);
	
	private final ResourceLocation id;
    private final DataResource<Material> material;
    private final HarvestTier harvestTier;
    private final TagKey<Block> equivalentIncorrectForToolTag;
    @Nullable private final TagKey<Block> additionalBlocksForTool;

    IceAndFireMaterials(String path, String levelHint, Tier equivalentTier) {
        this(path, path, levelHint, equivalentTier);
    }

    IceAndFireMaterials(String path, String levelHint, Tier equivalentTier, @Nullable TagKey<Block> additionalBlocksForTool) {
        this(path, path, levelHint, equivalentTier, additionalBlocksForTool);
    }

    IceAndFireMaterials(String path, String harvestTierName, String levelHint, Tier equivalentTier) {
        this(path, harvestTierName, levelHint, equivalentTier, null);
    }

    IceAndFireMaterials(String path, String harvestTierName, String levelHint, Tier equivalentTier, @Nullable TagKey<Block> additionalBlocksForTool) {
        this.id = getResourceLocation(IceAndFire.MOD_ID, path);
        this.material = DataResource.material(this.id);
        this.harvestTier = HarvestTier.create(harvestTierName, levelHint);
        this.equivalentIncorrectForToolTag = equivalentTier.getIncorrectBlocksForDrops();
        this.additionalBlocksForTool = additionalBlocksForTool;
    }

    public DataResource<Material> getMaterial() {
        return material;
    }

    public HarvestTier getHarvestTier() {
        return harvestTier;
    }

    // Used by data generators
    public void generateTag(Function<TagKey<Block>, IntrinsicHolderTagsProvider.IntrinsicTagAppender<Block>> tagProvider) {
        var intrinsicTagAppender = tagProvider.apply(this.harvestTier.incorrectForTool());
        intrinsicTagAppender.addTag(this.equivalentIncorrectForToolTag);
        if (this.additionalBlocksForTool != null) {
            intrinsicTagAppender.remove(this.additionalBlocksForTool);
        }
    }
    
    public ResourceLocation getResourceLocation(String name) {
    	if(name.contains(":")) {
    		return ResourceLocation.tryParse(name);
    	}
    	 else {
             throw new IllegalArgumentException("attempting to getResourceLocation without namespace");
         }
    }
    
    public ResourceLocation getResourceLocation(String namespace, String name) {
        return ResourceLocation.tryParse(namespace + ":" + name);
    }
}
