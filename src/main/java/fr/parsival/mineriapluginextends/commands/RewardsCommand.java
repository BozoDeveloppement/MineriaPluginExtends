package fr.parsival.mineriapluginextends.commands;

import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.database.IridiumUser;
import fr.parsival.mineriapluginextends.database.Team;
import fr.parsival.mineriapluginextends.gui.RewardsGUI;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import java.util.List;

@NoArgsConstructor
public class RewardsCommand<T extends Team, U extends IridiumUser<T>> extends Command<T, U> {
    public RewardsCommand(List<String> args, String description, String syntax, String permission, long cooldownInSeconds) {
        super(args, description, syntax, permission, cooldownInSeconds);
    }

    @Override
    public boolean execute(U user, T team, String[] arguments, IridiumTeams<T, U> iridiumTeams) {
        Player player = user.getPlayer();
        player.openInventory(new RewardsGUI<>(team, player.getOpenInventory().getTopInventory(), iridiumTeams).getInventory());
        return true;
    }

}
