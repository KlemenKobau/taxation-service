package si.kkobau;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.api.models.BetDto;
import si.kkobau.data.entities.Country;
import si.kkobau.data.entities.TaxationRule;
import si.kkobau.data.entities.Trader;
import si.kkobau.data.repositories.TraderRepository;
import si.kkobau.services.BettingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@QuarkusTest
public class BettingServiceTest {

    @InjectMock
    private TraderRepository traderRepository;

    @Inject
    private BettingService bettingService;

    private Trader mockTrader(TaxationRule taxationRule) {
        Country country = new Country();
        country.setId(1L);
        country.setTaxationRule(taxationRule);

        Trader trader = new Trader();
        trader.setId(1L);
        trader.setCountry(country);
        country.setTraders(List.of(trader));

        Mockito.when(traderRepository.findByIdOptional(trader.getId()))
                .thenReturn(Optional.of(trader));

        return trader;
    }

    private BetDto createTestBet() {
        BetDto play = new BetDto();
        play.setPlayedAmount(new BigDecimal("2.03"));
        play.setOdd(new BigDecimal("2.08"));
        return play;
    }

    @Test
    void testGeneralAmount() {
        Trader mock = mockTrader(TaxationRule.GENERAL_AMOUNT);
        mock.getCountry().setTaxAmount(new BigDecimal("1.1"));

        BetDto play = createTestBet();
        play.setTraderId(mock.getId());

        BetReturnInfoDto ret = bettingService.processBet(play);

        Assertions.assertEquals(0, new BigDecimal("4.22").compareTo(ret.getPossibleReturnAmountBefTax()));
        Assertions.assertEquals(0, new BigDecimal("3.12").compareTo(ret.getPossibleReturnAmountAfterTax()));
        Assertions.assertEquals(0, new BigDecimal("1.1").compareTo(ret.getTaxAmount()));
        Assertions.assertNull(ret.getTaxRate());
    }

    @Test
    void testGeneralRate() {
        Trader mock = mockTrader(TaxationRule.GENERAL_RATE);
        mock.getCountry().setTaxRate(new BigDecimal("0.1"));

        BetDto play = createTestBet();
        play.setTraderId(mock.getId());

        BetReturnInfoDto ret = bettingService.processBet(play);

        Assertions.assertEquals(0, new BigDecimal("4.22").compareTo(ret.getPossibleReturnAmountBefTax()));
        Assertions.assertEquals(0, new BigDecimal("3.8").compareTo(ret.getPossibleReturnAmountAfterTax()));
        Assertions.assertEquals(0, new BigDecimal("0.1").compareTo(ret.getTaxRate()));
        Assertions.assertNull(ret.getTaxAmount());
    }

    @Test
    void testWinningsAmount() {
        Trader mock = mockTrader(TaxationRule.WINNINGS_AMOUNT);
        mock.getCountry().setTaxAmount(new BigDecimal("1.1"));

        BetDto play = createTestBet();
        play.setTraderId(mock.getId());

        BetReturnInfoDto ret = bettingService.processBet(play);

        Assertions.assertEquals(0, new BigDecimal("4.22").compareTo(ret.getPossibleReturnAmountBefTax()));
        Assertions.assertEquals(0, new BigDecimal("3.12").compareTo(ret.getPossibleReturnAmountAfterTax()));
        Assertions.assertEquals(0, new BigDecimal("1.1").compareTo(ret.getTaxAmount()));
        Assertions.assertNull(ret.getTaxRate());
    }

    @Test
    void testWinningsRate() {
        Trader mock = mockTrader(TaxationRule.WINNINGS_RATE);
        mock.getCountry().setTaxRate(new BigDecimal("0.1"));

        BetDto play = createTestBet();
        play.setTraderId(mock.getId());

        BetReturnInfoDto ret = bettingService.processBet(play);

        Assertions.assertEquals(0, new BigDecimal("4.22").compareTo(ret.getPossibleReturnAmountBefTax()));
        Assertions.assertEquals(0, new BigDecimal("4").compareTo(ret.getPossibleReturnAmountAfterTax()));
        Assertions.assertEquals(0, new BigDecimal("0.1").compareTo(ret.getTaxRate()));
        Assertions.assertNull(ret.getTaxAmount());
    }
}
