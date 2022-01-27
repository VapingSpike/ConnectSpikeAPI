/**
 * Created by Spike
 * Created 27-01-2022 at 20:26
 */

package net.spike.api.Modules;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class HologramManager {

    public List<Hologram> hologramList = new ArrayList<>();

    public abstract void createHologram(String name, Location location, String... lines);

    public void deleteHologram(String name) throws IOException {
        if(this.getHologram(name) == null) {
            return;
        }
        Hologram hologram = this.getHologram(name);

        for(Player player : Bukkit.getOnlinePlayers()) {
            hologram.disable(player);
        }
        this.hologramList.remove(hologram);
    }

    public void reloadHolograms(Player player) throws IOException {
        for(Hologram hologram : this.hologramList) {
            if(hologram.getWorld() == player.getLocation().getWorld()) {
                hologram.enable(player);
            } else {
                hologram.disable(player);
            }
        }
    }

    public Hologram getHologram(String name) {
        for(Hologram hologram : this.hologramList) {
            if(hologram.getName().equalsIgnoreCase(name)) {
                return hologram;
            }
        }
        return null;
    }

}
