/**
 * Created by Spike
 * Created 25-01-2022 at 22:32
 */

package net.spike.api.Modules;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.IOException;

@Getter
@Setter
public abstract class Border {

    public String name;
    public World world;

    public boolean cancelsExit;
    public boolean canShrinkExpand;

    public int red;
    public int green;
    public int blue;

    public Location minLocation;
    public Location maxLocation;

    public Border(String name, World world, boolean cancelsExit, boolean canShrinkExpand, int red, int green, int blue, Location minLocation, Location maxLocation) {
        this.name = name;
        this.world = world;
        this.cancelsExit = cancelsExit;
        this.canShrinkExpand = canShrinkExpand;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.minLocation = minLocation;
        this.maxLocation = maxLocation;
    }

    public abstract void enable(Player player) throws IOException;

    public abstract void update(Player player) throws IOException;

    public abstract void disable(Player player) throws IOException;

}
