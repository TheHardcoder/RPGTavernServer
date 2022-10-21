package de.byedev.rpgtavern.webapi;

import de.byedev.rpgtavern.core.GameService;
import de.byedev.rpgtavern.core.UserService;
import de.byedev.rpgtavern.webapi.dto.GameDTO;
import de.byedev.rpgtavern.webapi.dto.GamesDTO;
import de.byedev.rpgtavern.webapi.dto.UserDTO;
import de.byedev.rpgtavern.webapi.dto.UsersDTO;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Component(service= GameWebService.class, scope = ServiceScope.PROTOTYPE)
@JaxrsResource
@Produces(MediaType.APPLICATION_JSON)
@JSONRequired
@Path("/api/games")
public class GameWebService {

    @Reference
    GameService gameService;

    @GET
    @Path("")
    public GamesDTO getGames() {
        return new GamesDTO(gameService.getGames().values().stream().map(u -> GameDTO.fromGame(u)).collect(Collectors.toList()));
    }

    @GET
    @Path("/{name}")
    public GameDTO getGame(@PathParam("name") String name) {
        return GameDTO.fromGame(gameService.getGames().get(name));
    }
}
