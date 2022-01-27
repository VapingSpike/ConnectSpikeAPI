/**
 * Created by Spike
 * Created 25-01-2022 at 22:08
 */

package net.spike.development.clans;

import lombok.Data;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Clans {

    private UUID uuid, leader;
    private String name, prefix;
    private ArrayList<String> members;
    private List<Player> online = new ArrayList<>();
    private ArrayList<String> invited;

    public Clans(UUID uuid) {
        this.uuid = uuid;
    }

}
