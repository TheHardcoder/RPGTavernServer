package de.byedev.rpgtavern.webapi;

import de.byedev.rpgtavern.webapi.dto.DiceDTO;
import de.byedev.rpgtavern.webapi.dto.DieDTO;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JSONRequired;
import org.osgi.service.jaxrs.whiteboard.propertytypes.JaxrsResource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component(service= DiceService.class, scope = ServiceScope.PROTOTYPE)
@JaxrsResource
@Produces(MediaType.APPLICATION_JSON)
@JSONRequired
@Path("/api/roll")
public class DiceService {

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public DiceDTO rollDice(DiceDTO dice)
    {
        dice.roll();
        return dice;
    }

    @POST
    @Path("/1")
    @Consumes(MediaType.APPLICATION_JSON)
    public DieDTO rollDie(DieDTO die)
    {
        die.roll();
        return die;
    }

    @GET
    @Path("/d6")
    public DiceDTO rollD6()
    {
        DiceDTO dice = new DiceDTO();
        dice.getDice().add(new DieDTO(6));
        dice.roll();
        return dice;
    }
}
