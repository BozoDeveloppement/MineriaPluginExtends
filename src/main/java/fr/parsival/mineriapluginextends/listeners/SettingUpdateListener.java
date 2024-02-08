package fr.parsival.mineriapluginextends.listeners;

import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.SettingType;
import fr.parsival.mineriapluginextends.api.SettingUpdateEvent;
import fr.parsival.mineriapluginextends.database.IridiumUser;
import fr.parsival.mineriapluginextends.database.Team;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

@AllArgsConstructor
public class SettingUpdateListener<T extends Team, U extends IridiumUser<T>> implements Listener {
    private final IridiumTeams<T, U> iridiumTeams;

    @EventHandler
    public void onSettingUpdate(SettingUpdateEvent<T, U> event) {
        if (event.getSetting().equalsIgnoreCase(SettingType.TIME.getSettingKey())) {
            iridiumTeams.getTeamManager().getTeamMembers(event.getTeam()).stream().map(U::getPlayer).filter(Objects::nonNull).forEach(player ->
                    iridiumTeams.getTeamManager().sendTeamTime(player)
            );
        }
        if (event.getSetting().equalsIgnoreCase(SettingType.WEATHER.getSettingKey())) {
            iridiumTeams.getTeamManager().getTeamMembers(event.getTeam()).stream().map(U::getPlayer).filter(Objects::nonNull).forEach(player ->
                    iridiumTeams.getTeamManager().sendTeamWeather(player)
            );
        }
    }

}
