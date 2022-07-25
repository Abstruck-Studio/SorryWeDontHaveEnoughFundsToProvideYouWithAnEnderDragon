package org.abstruck.swdheftpywaed;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.item.EnderCrystalEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.end.DragonFightManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Goulixiaoji
 */
@Mod(SorryWeDontHaveEnoughFundsToProvideYouWithAnEnderDragon.MOD_ID)
@Mod.EventBusSubscriber()
public class SorryWeDontHaveEnoughFundsToProvideYouWithAnEnderDragon {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "swdheftpywaed";

    public SorryWeDontHaveEnoughFundsToProvideYouWithAnEnderDragon() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void sorryWeDontHaveEnoughFundsToProvideYouWithAnEnderDragon(EntityJoinWorldEvent event) {
        Entity entity = event.getEntity();
        World level = event.getWorld();

        if (!level.isClientSide && level.dimension() == World.END) {

            if (entity.getClass() == EnderDragonEntity.class) {
                DragonFightManager dfm;
                dfm = ((EnderDragonEntity) entity).getDragonFight();
                if (!dfm.hasPreviouslyKilledDragon()) {
                    List<EnderCrystalEntity> list = level.getEntitiesOfClass(EnderCrystalEntity.class, entity.getBoundingBox().inflate(320.0D));
                    for (EnderCrystalEntity enderCrystal : list){
                        enderCrystal.kill();
                    }

                    ((EnderDragonEntity) entity).setHealth(0.0F);
                    dfm.setDragonKilled((EnderDragonEntity) entity);
                    LOGGER.debug("Good night Ender Dragon.");
                }
            }


        }

    }


}
