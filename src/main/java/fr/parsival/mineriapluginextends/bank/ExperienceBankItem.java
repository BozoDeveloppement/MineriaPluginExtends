package fr.parsival.mineriapluginextends.bank;

import com.iridium.iridiumcore.Item;
import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.database.TeamBank;
import fr.parsival.mineriapluginextends.utils.PlayerUtils;
import lombok.NoArgsConstructor;
import org.bukkit.entity.Player;

@NoArgsConstructor
public class ExperienceBankItem extends BankItem {

    public ExperienceBankItem(double defaultAmount, Item item) {
        super("experience", item, defaultAmount, true);
    }

    @Override
    public BankResponse withdraw(Player player, Number amount, TeamBank teamBank, IridiumTeams<?, ?> iridiumTeams) {
        int experience = Math.min(amount.intValue(), (int) teamBank.getNumber());
        if (experience > 0) {
            PlayerUtils.setTotalExperience(player, PlayerUtils.getTotalExperience(player) + experience);
            teamBank.setNumber(teamBank.getNumber() - experience);
            return new BankResponse(experience, true);
        }
        return new BankResponse(experience, false);
    }

    @Override
    public BankResponse deposit(Player player, Number amount, TeamBank teamBank, IridiumTeams<?, ?> iridiumTeams) {
        int experience = Math.min(amount.intValue(), PlayerUtils.getTotalExperience(player));
        if (experience > 0) {
            PlayerUtils.setTotalExperience(player, PlayerUtils.getTotalExperience(player) - experience);
            teamBank.setNumber(teamBank.getNumber() + experience);
            return new BankResponse(experience, true);
        }
        return new BankResponse(experience, false);
    }

}
