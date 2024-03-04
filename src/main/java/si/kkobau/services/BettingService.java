package si.kkobau.services;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.api.models.BetDto;
import si.kkobau.data.entities.Country;
import si.kkobau.data.entities.TaxationRule;
import si.kkobau.data.entities.Trader;
import si.kkobau.data.repositories.TraderRepository;
import si.kkobau.exceptions.NotFoundException;

import java.math.BigDecimal;

@ApplicationScoped
public class BettingService {

    private final TraderRepository traderRepository;

    @Inject
    public BettingService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public @Valid BetReturnInfoDto processBet(@Valid @NotNull BetDto betDto) {
        Trader trader = traderRepository.findByIdOptional(betDto.getTraderId())
                .orElseThrow(() -> new NotFoundException("Trader not found"));
        Country country = trader.getCountry();

        TaxationRule taxationRule = country.getTaxationRule();
        BigDecimal taxAmount = country.getTaxAmount();
        BigDecimal taxRate = country.getTaxRate();

        BetReturnInfoDto betReturnInfoDto = new BetReturnInfoDto();
        betReturnInfoDto.setTaxAmount(taxAmount);
        betReturnInfoDto.setTaxRate(taxRate);
        BigDecimal beforeTax = betDto.getPlayedAmount().multiply(betDto.getOdd());
        betReturnInfoDto.setPossibleReturnAmountBefTax(beforeTax);

        BetReturnInfoDto processed = switch (taxationRule) {
            case GENERAL_RATE -> processGeneralRate(betReturnInfoDto);
            case GENERAL_AMOUNT -> processGeneralAmount(betReturnInfoDto);
            case WINNINGS_RATE -> processWinningsRate(betReturnInfoDto, betDto.getPlayedAmount());
            case WINNINGS_AMOUNT -> processWinningsAmount(betReturnInfoDto);
        };

        processed.normalize();

        return processed;
    }

    private BetReturnInfoDto processGeneralRate(BetReturnInfoDto betReturnInfoDto) {
        BigDecimal taxRate = betReturnInfoDto.getTaxRate();
        BigDecimal beforeTax = betReturnInfoDto.getPossibleReturnAmountBefTax();

        BigDecimal taxAmount = taxRate.multiply(beforeTax);
        BigDecimal afterTax = beforeTax.subtract(taxAmount);

        betReturnInfoDto.setPossibleReturnAmountAfterTax(afterTax);

        return betReturnInfoDto;
    }

    private BetReturnInfoDto processGeneralAmount(BetReturnInfoDto betReturnInfoDto) {
        BigDecimal taxAmount = betReturnInfoDto.getTaxAmount();
        BigDecimal beforeTax = betReturnInfoDto.getPossibleReturnAmountBefTax();

        BigDecimal afterTax = beforeTax.subtract(taxAmount);
        betReturnInfoDto.setPossibleReturnAmountAfterTax(afterTax);

        return betReturnInfoDto;
    }

    private BetReturnInfoDto processWinningsRate(BetReturnInfoDto betReturnInfoDto, BigDecimal amountPlayed) {
        BigDecimal taxRate = betReturnInfoDto.getTaxRate();
        BigDecimal beforeTax = betReturnInfoDto.getPossibleReturnAmountBefTax();
        BigDecimal winnings = beforeTax.subtract(amountPlayed);

        BigDecimal taxAmount = taxRate.multiply(winnings);
        BigDecimal afterTax = beforeTax.subtract(taxAmount);

        betReturnInfoDto.setPossibleReturnAmountAfterTax(afterTax);

        return betReturnInfoDto;
    }

    private BetReturnInfoDto processWinningsAmount(BetReturnInfoDto betReturnInfoDto) {
        return processGeneralAmount(betReturnInfoDto);
    }
}
