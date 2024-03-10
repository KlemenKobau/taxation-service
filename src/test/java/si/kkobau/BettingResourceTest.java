package si.kkobau;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import si.kkobau.api.models.BetDto;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.exceptions.NotFoundException;
import si.kkobau.services.BettingService;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class BettingResourceTest {

    @InjectMock
    private BettingService bettingService;

    @Test
    public void testIntegration() {
        BetReturnInfoDto result = createResult();

        if (useMocks()) {
            Mockito.when(bettingService.processBet(any(BetDto.class)))
                    .thenReturn(result);
        }

        given()
                .contentType(ContentType.JSON)
                .body(createTestBet())
                .when()
                .post(getApiRootPath() + "/bets")
                .then()
                .statusCode(200)
                .body(notNullValue(BetReturnInfoDto.class));
    }

    @Test
    public void testNull() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .post(getApiRootPath() + "/bets")
                .then()
                .statusCode(400);
    }

    @Test
    public void testNotFound() {
        BetDto testBet = createTestBet();
        testBet.setTraderId(2L);

        if (useMocks()) {
            Mockito.when(bettingService.processBet(any(BetDto.class)))
                    .thenThrow(new NotFoundException("Trader not found."));
        }

        given()
                .contentType(ContentType.JSON)
                .body(testBet)
                .when()
                .post(getApiRootPath() + "/bets")
                .then()
                .statusCode(404);
    }

    protected boolean useMocks() {
        return true;
    }

    protected String getApiRootPath() {
        return "api/v1";
    }

    protected BetDto createTestBet() {
        BetDto play = new BetDto();
        play.setPlayedAmount(new BigDecimal("2.03"));
        play.setOdd(new BigDecimal("2.08"));
        play.setTraderId(1L);
        return play;
    }

    protected BetReturnInfoDto createResult() {
        BetReturnInfoDto infoDto = new BetReturnInfoDto();
        infoDto.setPossibleReturnAmountBefTax(new BigDecimal("4.22"));
        infoDto.setPossibleReturnAmountAfterTax(new BigDecimal("4.18"));
        infoDto.setTaxRate(new BigDecimal("0.01"));
        infoDto.setTaxAmount(null);

        return infoDto;
    }
}
