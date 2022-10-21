package de.byedev.rpgtavern.webapi;

import de.byedev.rpgtavern.core.UserService;
import de.byedev.rpgtavern.core.exceptions.UserCreationException;
import de.byedev.rpgtavern.webapi.dto.UserDTO;
import de.byedev.rpgtavern.webapi.dto.UsersDTO;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Component(service= UserWebService.class, scope = ServiceScope.PROTOTYPE)
@JaxrsResource
@Produces(MediaType.APPLICATION_JSON)
@JSONRequired
@Path("/api/users")
public class UserWebService {

    @Reference
    UserService userService;


    @GET
    @Path("")
    public UsersDTO getUsers() {
        return new UsersDTO(userService.getUsers().stream().map(u -> UserDTO.fromUser(u)).collect(Collectors.toList()));
    }

    @POST
    @Path("")
    public UserDTO createUser(@FormParam("name")String name, @FormParam("email")String email, @FormParam("password")String password) throws UserCreationException {
        userService.createAccount(name, email, password.toCharArray());
        return UserDTO.fromUser(userService.findUserByEmail(email).orElseGet(() -> null));
    }

    @GET
    @Path("{input}")
    public UserDTO getUser(@PathParam("input") String name) {
        return UserDTO.fromUser(userService.getUsers().stream().filter(u -> u.getUsername().equals(name)).findAny().orElseGet(() -> null));
    }
}
