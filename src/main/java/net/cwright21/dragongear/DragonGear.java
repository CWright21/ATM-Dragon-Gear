package net.cwright21.dragongear;

import net.cwright21.dragongear.material.DragonMaterialsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(DragonGear.NAMESPACE)
@EventBusSubscriber(modid = DragonGear.NAMESPACE)
public class DragonGear {
    public static final String NAMESPACE = "dragon_gear";

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        gen.addProvider(true, new DragonMaterialsProvider(gen));
    }
}