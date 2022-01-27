/**
 * Created by Spike
 * Created 25-01-2022 at 22:21
 */

package net.spike.api.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.GameMode;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
public class User {

    /* Global Data */
    private final UUID uuid;
    private final String name;

    /* Session Data */
    private boolean lunarClient = false;
    private boolean checking = false;
    private GameMode lastGameMode = GameMode.SURVIVAL;
    private boolean wasFlying = false;
    private boolean wasAllowFlight = false;

}
