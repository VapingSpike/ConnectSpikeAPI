/**
 * Created by Spike
 * Created 27-01-2022 at 20:30
 */

package net.spike.api.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class BaseEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public boolean call(JavaPlugin javaPlugin) {
        javaPlugin.getServer().getPluginManager().callEvent(this);
        return this instanceof Cancellable && ((Cancellable) this).isCancelled();
    }

}

