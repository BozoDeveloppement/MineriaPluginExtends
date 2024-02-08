package fr.parsival.mineriapluginextends.listeners;

import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.database.IridiumUser;
import fr.parsival.mineriapluginextends.database.Team;
import fr.parsival.mineriapluginextends.database.TeamEnhancement;
import fr.parsival.mineriapluginextends.enhancements.Enhancement;
import fr.parsival.mineriapluginextends.enhancements.SpawnerEnhancementData;
import lombok.AllArgsConstructor;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.SpawnerSpawnEvent;

@AllArgsConstructor
public class SpawnerSpawnListener<T extends Team, U extends IridiumUser<T>> implements Listener {
    private final IridiumTeams<T, U> iridiumTeams;

    @EventHandler(ignoreCancelled = true)
    public void onCreatureSpawn(SpawnerSpawnEvent event) {
        iridiumTeams.getTeamManager().getTeamViaLocation(event.getLocation()).ifPresent(team -> {
            Enhancement<SpawnerEnhancementData> spawnerEnhancement = iridiumTeams.getEnhancements().spawnerEnhancement;
            TeamEnhancement teamEnhancement = iridiumTeams.getTeamManager().getTeamEnhancement(team, "spawner");
            SpawnerEnhancementData data = spawnerEnhancement.levels.get(teamEnhancement.getLevel());
            CreatureSpawner spawner = event.getSpawner();

            if (!teamEnhancement.isActive(spawnerEnhancement.type)) return;
            if (data == null) return;

            spawner.setSpawnCount(data.spawnCount);
            spawner.update(true);
        });
    }
}
