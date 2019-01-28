package io.github.eirikh1996.arenamovecraft;

import mc.alk.arena.objects.ArenaPlayer;
import mc.alk.arena.objects.arenas.Arena;
import mc.alk.arena.objects.events.ArenaEventHandler;
import net.countercraft.movecraft.events.CraftSinkEvent;

public class MovecraftArena extends Arena {

    @ArenaEventHandler
    public void onCraftSink(CraftSinkEvent event){

        ArenaPlayer ap = null;
        try {
            ap = new ArenaPlayer(event.getCraft().getNotificationPlayer());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ap.setHealth(0.0);

    }
}
