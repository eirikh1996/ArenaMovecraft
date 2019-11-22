package io.github.eirikh1996.arenamovecraft;

import mc.alk.arena.objects.ArenaPlayer;
import mc.alk.arena.objects.arenas.Arena;
import mc.alk.arena.objects.events.ArenaEventHandler;
import net.countercraft.movecraft.events.CraftSinkEvent;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MovecraftArena extends Arena {

    private final HashMap<UUID, Integer> craftsLeft = new HashMap<>();
    private int maxCraftsPerPlayer;
    private WinCondition winCondition;
    @Override
    protected void onBegin() {
        super.onBegin();
        for (ArenaPlayer player : getAlivePlayers()){
            craftsLeft.put(player.getID(), maxCraftsPerPlayer);
        }
    }

    @ArenaEventHandler
    public void onCraftSink(CraftSinkEvent event){
        ArenaPlayer player = getPlayer(event.getCraft().getNotificationPlayer());
        if (winCondition == WinCondition.CRAFTS_LEFT){
            int craftsLeft = this.craftsLeft.get(player.getID());
            if (craftsLeft > 0){
                craftsLeft--;
                this.craftsLeft.put(player.getID(), craftsLeft);
                return;
            }
            player.setHealth(0.0);
        } else if (winCondition == WinCondition.SINK_OPPONENTS){
            player.setHealth(0.0);
        }

    }

    public WinCondition getWinCondition() {
        return winCondition;
    }

    public void setWinCondition(WinCondition winCondition) {
        this.winCondition = winCondition;
    }

    public void setMaxCraftsPerPlayer(int maxCraftsPerPlayer) {
        this.maxCraftsPerPlayer = maxCraftsPerPlayer;
    }

    private ArenaPlayer getPlayer(Player player){
        for (ArenaPlayer arenaPlayer : getAlivePlayers()){
            if (arenaPlayer.getPlayer().equals(player)){
                return arenaPlayer;
            }
        }
        return null;
    }
}
