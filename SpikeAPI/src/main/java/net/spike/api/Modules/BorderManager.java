/**
 * Created by Spike
 * Created 25-01-2022 at 22:34
 */

package net.spike.api.Modules;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class BorderManager {

    public List<Border> borderList = new ArrayList<>();

    public abstract void createBorder(String name, World world, boolean cancelsExit, boolean canShrinkExpand, int red, int green, int blue, Location minLocation, Location maxLocation);

    public void deleteBorder(String name) {
        if(this.getBorder(name) == null) {
            return;
        }
        Border border = this.getBorder(name);

        for(Player player : Bukkit.getOnlinePlayers()) {
            try {
                border.disable(player);
            } catch(IOException e) {
                //ignore
            }
        }
        this.borderList.remove(border);
    }

    public Border getBorder(String name) {
        for(Border border : this.borderList) {
            if(border.getName().equalsIgnoreCase(name)) {
                return border;
            }
        }
        return null;
    }

}
