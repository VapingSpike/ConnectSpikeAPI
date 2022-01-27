/**
 * Created by Spike
 * Created 26-01-2022 at 15:32
 */

package net.spike.api.Modules;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public abstract class Hologram {

    public UUID uuid;
    public String name;
    public World world;
    public List<UUID> enabled;
    public int x;
    public int y;
    public int z;
    public List<String> lines;

    public Hologram(UUID uuid, String name, int x, int y, int z, World world, String... lines) {
        this.uuid = uuid;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.lines = new ArrayList<>();
        this.enabled = new ArrayList<>();
        this.lines.addAll(Arrays.asList(lines));
    }

    public void addLine(String text, int index) {
        this.lines.add(index, text);
    }

    public void removeLine(int index) {
        this.lines.remove(index);
    }

    public abstract void enable(Player player) throws IOException;
    public abstract void update(Player player) throws IOException;
    public abstract void disable(Player player) throws IOException;

}
