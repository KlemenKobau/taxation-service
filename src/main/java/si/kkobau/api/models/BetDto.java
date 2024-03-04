package si.kkobau.api.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class BetDto {

    @NotNull
    private Long traderId;

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal playedAmount;

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal odd;

    public Long getTraderId() {
        return traderId;
    }

    public void setTraderId(Long traderId) {
        this.traderId = traderId;
    }

    public BigDecimal getPlayedAmount() {
        return playedAmount;
    }

    public void setPlayedAmount(BigDecimal playedAmount) {
        this.playedAmount = playedAmount;
    }

    public BigDecimal getOdd() {
        return odd;
    }

    public void setOdd(BigDecimal odd) {
        this.odd = odd;
    }
}
