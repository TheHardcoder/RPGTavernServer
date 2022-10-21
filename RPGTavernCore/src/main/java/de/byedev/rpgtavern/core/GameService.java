package de.byedev.rpgtavern.core;

import de.byedev.rpgtavern.persistence.Storage;
import de.byedev.rpgtavern.persistence.entities.Game;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component(service = GameService.class)
public class GameService {

    @Reference
    private Storage storage;

    public Map<String, Game> getGames() {
        return storage.getRoot().getGames();
    }
}
