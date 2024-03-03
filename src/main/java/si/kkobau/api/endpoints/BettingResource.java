package si.kkobau.api.endpoints;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.api.models.PlayDto;
import si.kkobau.services.BettingService;

@Path("/bets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BettingResource {

    private final BettingService bettingService;

    public BettingResource(BettingService bettingService) {
        this.bettingService = bettingService;
    }

    @POST
    public BetReturnInfoDto play(@Valid PlayDto play) {
        return this.bettingService.processPlay(play);
    }
}
