package org.goldenpositioning.classes.LISTENER;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

import static org.goldenpositioning.classes.COMMANDS.null_COMMAND.permanent_saves;
import static org.goldenpositioning.classes.COMMANDS.null_COMMAND.permanent_saves_config;

public class PlayerJoin_LISTENER implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if(!permanent_saves.exists()) {
            permanent_saves_config.set(event.getPlayer().getUniqueId() + ".saves." + "name", " ");
            permanent_saves_config.set(event.getPlayer().getUniqueId() + ".saves." + "name", null);
            try {
                permanent_saves_config.save(permanent_saves);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
