/**
 * Created by Spike
 * Created 24-01-2022 at 18:31
 */

package net.spike.development.utilities;

import com.google.common.collect.Lists;
import org.bukkit.ChatColor;

import java.util.List;

public class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("Cannot instantiate Util Class...");

    }

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> format(List<String> messages) {
        List<String> toReturn = Lists.newArrayList();

        messages.forEach(message -> toReturn.add(format(message)));

        return toReturn;
    }

}
