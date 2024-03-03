package si.kkobau.api.models;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

import java.math.BigDecimal;

public class BetReturnInfoDto {

    // FIXME this field is here for future expansion
    @Null
    @Digits(integer = Integer.MAX_VALUE, fraction = 2)
    private BigDecimal possibleReturnAmount;

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

    public BigDecimal getPossibleReturnAmount() {
        return possibleReturnAmount;
    }

    public void setPossibleReturnAmount(BigDecimal possibleReturnAmount) {
        this.possibleReturnAmount = possibleReturnAmount;
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
