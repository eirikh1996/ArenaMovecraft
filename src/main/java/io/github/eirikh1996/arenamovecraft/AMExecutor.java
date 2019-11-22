package io.github.eirikh1996.arenamovecraft;


import mc.alk.arena.executors.CustomCommandExecutor;
import mc.alk.arena.executors.MCCommand;
import org.bukkit.entity.Player;

public class AMExecutor extends CustomCommandExecutor {

    @MCCommand(cmds = {"winCondition"}, admin = true)
    public static boolean winCondition(Player sender, MovecraftArena arena, String winCondition){
        arena.setWinCondition(WinCondition.getWinCondition(winCondition));
        return true;
    }

    @MCCommand(cmds = {"maxCrafts"}, admin = true)
    public static boolean maxCrafts(Player sender, MovecraftArena arena, Integer maxCrafts){
        if (!arena.getWinCondition().equals(WinCondition.CRAFTS_LEFT)){
            sender.sendMessage("This arena does not have the crafts_left win condition");
            return true;
        }
        arena.setMaxCraftsPerPlayer(maxCrafts);
        return true;
    }
}
