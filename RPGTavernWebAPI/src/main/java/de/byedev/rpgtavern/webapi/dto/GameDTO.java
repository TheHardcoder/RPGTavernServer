package de.byedev.rpgtavern.webapi.dto;

import de.byedev.rpgtavern.persistence.entities.Game;

import java.util.List;
import java.util.stream.Collectors;

public class GameDTO {

    private List<String> combatants;

    public static GameDTO fromGame(Game game) {
        if (game == null)
            return null;
        GameDTO gameDTO = new GameDTO();
        gameDTO.combatants = game.getCombatants().stream().map(combatant -> combatant.getName()).collect(Collectors.toList());
        return gameDTO;
    }
}
