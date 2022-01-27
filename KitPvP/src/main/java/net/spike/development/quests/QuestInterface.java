/**
 * Created by Spike
 * Created 25-01-2022 at 20:15
 */

package net.spike.development.quests;

import org.bukkit.entity.Player;

import java.util.List;

public interface QuestInterface {

    List<String> quests(Player player);
    String getActiveQuest(Player player);
    int getQuestGoal(Player player);
    int getQuestCompletion(Player player);
    double getQuestProgress(Player player);
    void onQuestComplete(Player player);

}
