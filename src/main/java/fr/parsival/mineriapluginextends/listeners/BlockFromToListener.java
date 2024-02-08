package fr.parsival.mineriapluginextends.listeners;

import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.database.IridiumUser;
import fr.parsival.mineriapluginextends.database.Team;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

import java.util.Optional;

@AllArgsConstructor
public class BlockFromToListener<T extends Team, U extends IridiumUser<T>> implements Listener {
    private final IridiumTeams<T, U> iridiumTeams;

    @EventHandler(ignoreCancelled = true)
    public void onBlockFromTo(BlockFromToEvent event) {
        int currentTeam = iridiumTeams.getTeamManager().getTeamViaLocation(event.getBlock().getLocation()).map(T::getId).orElse(0);
        Optional<T> team = iridiumTeams.getTeamManager().getTeamViaLocation(event.getToBlock().getLocation());
        if (team.map(T::getId).orElse(currentTeam) != currentTeam) {
            event.setCancelled(true);
        }
    }

}