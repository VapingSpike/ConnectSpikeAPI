/**
 * Created by Spike
 * Created 25-01-2022 at 22:23
 */

package net.spike.api.User;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter @Setter
public class UserManager {

    /* User Maps */
    private Map<UUID, String> playerNameCache;
    private Map<UUID, User> playerDataMap;

    public UserManager() {
        this.playerDataMap = new HashMap<>();
        this.playerNameCache = new HashMap<>();
    }

    /**
     * Get the player user object by UUID
     *
     * @param uuid
     * @return
     */
    public User getPlayerData(UUID uuid) {
        return this.playerDataMap.get(uuid);
    }

    /**
     * Get the player user by player object.
     *
     * @param player
     * @return
     */
    public User getPlayerData(Player player) {
        if(!this.playerNameCache.containsKey(player.getUniqueId())) {
            this.playerNameCache.put(player.getUniqueId(), player.getName());
        }
        return this.getPlayerData(player.getUniqueId());
    }

    /**
     * Set the player user object in the HashMap by the UUID
     *
     * @param uuid
     * @param data
     */
    public void setPlayerData(UUID uuid, User data) {
        this.playerDataMap.put(uuid, data);
    }

    /**
     * Remove the player user object from the HashMap.
     *
     * @param uuid
     */
    public void removePlayerData(UUID uuid) {
        this.playerDataMap.remove(uuid);
    }

}
