package si.kkobau;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import si.kkobau.api.models.PlayDto;
import si.kkobau.services.BettingService;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class BettingResourceTest implements IResourceTest {

    @InjectMock
    private BettingService bettingService;

    private PlayDto createTestPlay() {
        PlayDto play = new PlayDto();
        play.setPlayedAmount(new BigDecimal("2.03"));
        play.setOdd(new BigDecimal("2.08"));
        play.setTraderId(1L);
        return play;
    }

    @Test
    public void testIntegration() {
        given()
                .contentType(ContentType.JSON)
                .body(createTestPlay())
                .when()
                .post(getApiRootPath() + "/bets")
                .then()
                .statusCode(200);
    }
}