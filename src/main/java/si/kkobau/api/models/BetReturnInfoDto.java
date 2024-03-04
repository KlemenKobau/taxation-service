package si.kkobau.api.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BetReturnInfoDto {

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal possibleReturnAmountBefTax;

    @NotNull
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal possibleReturnAmountAfterTax;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal taxRate;

    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal taxAmount;

    public void normalize() {
        this.possibleReturnAmountBefTax = this.possibleReturnAmountBefTax.setScale(2, RoundingMode.HALF_UP);
        this.possibleReturnAmountAfterTax = this.possibleReturnAmountAfterTax.setScale(2, RoundingMode.HALF_UP);

        if (this.taxRate != null) {
            this.taxRate = this.taxRate.setScale(2, RoundingMode.HALF_UP);
        }
        if (this.taxAmount != null) {
            this.taxAmount = this.taxAmount.setScale(2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal getPossibleReturnAmountBefTax() {
        return possibleReturnAmountBefTax;
    }

    public void setPossibleReturnAmountBefTax(BigDecimal possibleReturnAmountBefTax) {
        this.possibleReturnAmountBefTax = possibleReturnAmountBefTax;
    }

    public BigDecimal getPossibleReturnAmountAfterTax() {
        return possibleReturnAmountAfterTax;
    }

    public void setPossibleReturnAmountAfterTax(BigDecimal possibleReturnAmountAfterTax) {
        this.possibleReturnAmountAfterTax = possibleReturnAmountAfterTax;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
}
