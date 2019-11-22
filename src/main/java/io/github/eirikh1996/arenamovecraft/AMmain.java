package io.github.eirikh1996.arenamovecraft;

import mc.alk.arena.BattleArena;
import net.countercraft.movecraft.Movecraft;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AMmain extends JavaPlugin {
    private BattleArena battleArenaPlugin;
    private Movecraft movecraftPlugin;
    private static AMmain instance;
    private Logger logger = getLogger();
    @Override
    public void onEnable() {

        //check for BattleArena
        Plugin tempBattleArenaPlugin = getServer().getPluginManager().getPlugin("BattleArena");
        if (tempBattleArenaPlugin != null){
            if (tempBattleArenaPlugin instanceof BattleArena){
                battleArenaPlugin = (BattleArena) tempBattleArenaPlugin;
                logger.info("Found a compatible version of BattleArena");
            }
        }

        //Disable if BattleArena is not found
        if (battleArenaPlugin == null){
            logger.severe("BattleArena is required for this plugin, but a compatible version of BattleArena was not found");
            getServer().getPluginManager().disablePlugin(this);
        }
        //check for Movecraft
        Plugin tempMovecraftPlugin = getServer().getPluginManager().getPlugin("Movecraft");
        if (tempMovecraftPlugin != null){
            if (tempMovecraftPlugin instanceof Movecraft){
                movecraftPlugin = (Movecraft) tempMovecraftPlugin;
                logger.info("Found a compatible version of Movecraft");
            }
        }
        //Disable if Movecraft is not found
        if (movecraftPlugin == null){
            logger.severe("Movecraft is required for this plugin, but a compatible version of Movecraft was not found");
            getServer().getPluginManager().disablePlugin(this);
        }
        //If Movecraft was found, but was not enabled, disable this too
        else if (!movecraftPlugin.isEnabled()){
            logger.severe("Movecraft is required for this plugin. While a compatible version of it was found, it was disabled");
            getServer().getPluginManager().disablePlugin(this);
        }
        saveResource("MovecraftArenaConfig.yml", false);
        //register arena
        BattleArena.registerCompetition(this, "MovecraftArena", "movecraftarena", MovecraftArena.class);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onLoad() {
        instance = this;
    }

    public Movecraft getMovecraftPlugin(){
        return movecraftPlugin;
    }

    public BattleArena getBattleArenaPlugin(){
        return battleArenaPlugin;
    }

    public static synchronized AMmain getInstance(){
        return instance;
    }
}
