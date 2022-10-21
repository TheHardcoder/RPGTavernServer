package de.byedev.rpgtavern.webapi.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement(name="Users")
public class GamesDTO {

    private Collection<GameDTO> games;

    public GamesDTO(Collection<GameDTO> games) {
        this.games = games;
    }

    @XmlElement(name="Item")
    public Collection<GameDTO> getGames() {
        return games;
    }
}
