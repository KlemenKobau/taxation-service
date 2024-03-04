package si.kkobau.api.endpoints;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.api.models.BetDto;
import si.kkobau.exceptions.ExceptionResponse;
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
    @Operation(
        summary = "Process a bet"
    )
    @APIResponses(
        value = {
                @APIResponse(description = "Processed bet", responseCode = "200"),
                @APIResponse(description = "Bad request, look at the message", responseCode = "400", content = {
                        @Content(schema = @Schema(implementation = ExceptionResponse.class))
                })
        }
    )
    public BetReturnInfoDto bet(@Valid @NotNull BetDto bet) {
        return this.bettingService.processBet(bet);
    }
}
