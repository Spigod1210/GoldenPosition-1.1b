package org.goldenpositioning.classes.LISTENER;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.goldenpositioning.classes.COMMANDS.null_COMMAND.*;

public class PlayerLeave_LISTENER implements Listener {

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if(!save_temp_x.isEmpty()) {
            save_temp_x.clear();
        }
        if(!save_temp_y.isEmpty()) {
            save_temp_y.clear();
        }
        if(!save_temp_z.isEmpty()) {
            save_temp_z.clear();
        }
    }
}
