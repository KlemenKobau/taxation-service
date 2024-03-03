package si.kkobau.api.endpoints;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.common.NotImplementedYet;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.api.models.PlayDto;

@Path("/bets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BettingResource {

    @POST
    public BetReturnInfoDto play(@Valid PlayDto play) {
        throw new NotImplementedYet();
    }
}
