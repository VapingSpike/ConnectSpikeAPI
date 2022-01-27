/**
 * Created by Spike
 * Created 24-01-2022 at 17:09
 */

package net.spike.development.utilities;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class SignHandler implements Listener {

    private final Multimap<UUID, SignChance> signUpdateMap;
    private final JavaPlugin plugin;

    public SignHandler(final JavaPlugin plugin) {
        super();
        this.signUpdateMap = HashMultimap.create();
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerKick(final PlayerQuitEvent e) {
        this.cancelTasks(e.getPlayer(), null, false);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onPlayerQuit(final PlayerQuitEvent e) {
        this.cancelTasks(e.getPlayer(), null, false);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onWorldChange(final PlayerChangedWorldEvent e) {
        this.cancelTasks(e.getPlayer(), null, false);
    }

    public boolean showLines(final Player player, final Sign sign, final String[] newLines, final long ticks, final boolean forceChange) {
        final String[] lines = sign.getLines();
        if(Arrays.equals(lines, newLines)) {
            return false;
        }
        final Collection<SignChance> signChances = this.getSignChanges(player);
        final Iterator<SignChance> iterator = signChances.iterator();
        while(iterator.hasNext()) {
            final SignChance signChance = iterator.next();
            if(signChance.sign.equals(sign)) {
                if(!forceChange && Arrays.equals(signChance.newLines, newLines)) {
                    return false;
                }
                signChance.runnable.cancel();
                iterator.remove();
                break;
            }
        }
        final Location location = sign.getLocation();
        player.sendSignChange(location, newLines);
        final SignChance signChance2;
        if(signChances.add(signChance2 = new SignChance(sign, newLines))) {
            final Block block = sign.getBlock();
            final BlockState previous = block.getState();
            final BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if(SignHandler.this.signUpdateMap.remove(player.getUniqueId(), signChance2) && previous.equals(block.getState())) {
                        player.sendSignChange(location, lines);
                    }
                }
            };
            runnable.runTaskLater(this.plugin, ticks);
            signChance2.runnable = runnable;
        }
        return true;
    }

    public Collection<SignChance> getSignChanges(final Player player) {
        return this.signUpdateMap.get(player.getUniqueId());
    }

    public void cancelTasks(final Sign sign) {
        final Iterator<SignChance> iterator = this.signUpdateMap.values().iterator();
        while(iterator.hasNext()) {
            final SignChance signChance = iterator.next();
            if(sign == null || signChance.sign.equals(sign)) {
                signChance.runnable.cancel();
                signChance.sign.update();
            }
        }
    }

    public void cancelTasks(final Player player, final Sign sign, final boolean revertLines) {
        final UUID uuid = player.getUniqueId();
        final Iterator<Map.Entry<UUID, SignChance>> iterator = this.signUpdateMap.entries().iterator();
        while(iterator.hasNext()) {
            final Map.Entry<UUID, SignChance> entry = iterator.next();
            if(entry.getKey().equals(uuid)) {
                final SignChance signChance = entry.getValue();
                if(sign != null && !signChance.sign.equals(sign)) {
                    continue;
                }
                if(revertLines) {
                    player.sendSignChange(signChance.sign.getLocation(), signChance.sign.getLines());
                }
                signChance.runnable.cancel();
                iterator.remove();
            }
        }
    }

    private static final class SignChance {
        public final Sign sign;
        public final String[] newLines;
        public BukkitRunnable runnable;

        public SignChance(final Sign sign, final String[] newLines) {
            super();
            this.sign = sign;
            this.newLines = newLines;
        }
    }

}
