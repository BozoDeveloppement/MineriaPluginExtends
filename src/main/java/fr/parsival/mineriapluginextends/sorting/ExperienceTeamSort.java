package fr.parsival.mineriapluginextends.sorting;

import com.iridium.iridiumcore.Item;
import fr.parsival.mineriapluginextends.IridiumTeams;
import fr.parsival.mineriapluginextends.database.Team;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class ExperienceTeamSort<T extends Team> extends TeamSorting<T> {

    public ExperienceTeamSort(Item item) {
        this.item = item;
        this.enabled = true;
    }

    @Override
    public List<T> getSortedTeams(IridiumTeams<T, ?> iridiumTeams) {
        return iridiumTeams.getTeamManager().getTeams().stream()
                .sorted(Comparator.comparing(T::getExperience).reversed())
                .collect(Collectors.toList());
    }
}
