package net.cwright21.dragongear;

import net.cwright21.dragongear.event.ServerEvents;
import net.cwright21.dragongear.material.DragonMaterialsProvider;
import net.cwright21.dragongear.trait.CustomTraitsProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import com.iafenvoy.uranus.event.LivingEntityEvents;


@Mod(DragonGear.NAMESPACE)
@EventBusSubscriber(modid = DragonGear.NAMESPACE)
public class DragonGear {
    public static final String NAMESPACE = "dragongear";

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        gen.addProvider(true, new DragonMaterialsProvider(gen));
        gen.addProvider(true, new CustomTraitsProvider(gen));
        event.createProvider(CustomLanguageProvider::new);
        
        LivingEntityEvents.DAMAGE.register(ServerEvents::onEntityDamage);
    }
    
}