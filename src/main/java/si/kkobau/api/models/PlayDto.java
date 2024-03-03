package si.kkobau.api.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PlayDto {
    @NotNull
    private int traderId;
    @NotNull
    private int playedAmount;
    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal odd;

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public int getPlayedAmount() {
        return playedAmount;
    }

    public void setPlayedAmount(int playedAmount) {
        this.playedAmount = playedAmount;
    }

    public BigDecimal getOdd() {
        return odd;
    }

    public void setOdd(BigDecimal odd) {
        this.odd = odd;
    }
}
