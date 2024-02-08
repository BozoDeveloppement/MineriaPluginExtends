package fr.parsival.mineriapluginextends.commands;

import com.iridium.iridiumcore.utils.StringUtils;
import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.database.IridiumUser;
import fr.parsival.mineriapluginextends.database.Team;
import fr.parsival.mineriapluginextends.database.TeamEnhancement;
import fr.parsival.mineriapluginextends.enhancements.Enhancement;
import fr.parsival.mineriapluginextends.enhancements.EnhancementType;
import fr.parsival.mineriapluginextends.gui.UpgradesGUI;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

import java.util.List;

@NoArgsConstructor
public class UpgradesCommand<T extends Team, U extends IridiumUser<T>> extends Command<T, U> {
    public UpgradesCommand(List<String> args, String description, String syntax, String permission, long cooldownInSeconds) {
        super(args, description, syntax, permission, cooldownInSeconds);
    }

    @Override
    public boolean execute(U user, T team, String[] args, IridiumTeams<T, U> iridiumTeams) {
        Player player = user.getPlayer();
        if (args.length == 0) {
            player.openInventory(new UpgradesGUI<>(team, player.getOpenInventory().getTopInventory(), iridiumTeams).getInventory());
            return true;
        }
        if (args.length != 2 || !args[0].equalsIgnoreCase("buy")) {
            player.sendMessage(StringUtils.color(syntax.replace("%prefix%", iridiumTeams.getConfiguration().prefix)));
            return false;
        }
        String booster = args[1];
        Enhancement<?> enhancement = iridiumTeams.getEnhancementList().get(booster);
        if (enhancement == null || enhancement.type != EnhancementType.UPGRADE) {
            player.sendMessage(StringUtils.color(iridiumTeams.getMessages().noSuchUpgrade
                    .replace("%prefix%", iridiumTeams.getConfiguration().prefix)
            ));
            return false;
        }
        TeamEnhancement teamEnhancement = iridiumTeams.getTeamManager().getTeamEnhancement(team, booster);
        if(enhancement.levels.get(teamEnhancement.getLevel() + 1) == null){
            player.sendMessage(StringUtils.color(iridiumTeams.getMessages().maxUpgradeLevelReached
                    .replace("%prefix%", iridiumTeams.getConfiguration().prefix)
            ));
            return false;
        }
        boolean success = iridiumTeams.getTeamManager().UpdateEnhancement(team, booster, player);
        if (success) {
            player.sendMessage(StringUtils.color(iridiumTeams.getMessages().purchasedUpgrade
                    .replace("%prefix%", iridiumTeams.getConfiguration().prefix)
                    .replace("%upgrade%", booster)
            ));
        }
        return success;
    }
}
