/**
 * Created by Spike
 * Created 27-01-2022 at 20:30
 */

package net.spike.api.event.impl;

import net.spike.api.event.PlayerEvent;
import org.bukkit.entity.Player;

public class AuthenticateEvent extends PlayerEvent {

    public AuthenticateEvent(Player player) {
        super(player);
    }

}

