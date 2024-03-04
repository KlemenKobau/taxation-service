package si.kkobau.data.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "COUNTRY")
public class Country extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaxationRule taxationRule;

    @Digits(integer=Integer.MAX_VALUE, fraction=2)
    @Column(precision=7, scale = 2)
    private BigDecimal taxRate;

    @Digits(integer=Integer.MAX_VALUE, fraction=2)
    @Column(precision=7, scale = 2)
    private BigDecimal taxAmount;

    @OneToMany(mappedBy = "country")
    private List<Trader> traders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaxationRule getTaxationRule() {
        return taxationRule;
    }

    public void setTaxationRule(TaxationRule taxationRule) {
        this.taxationRule = taxationRule;
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

    public List<Trader> getTraders() {
        return traders;
    }

    public void setTraders(List<Trader> traders) {
        this.traders = traders;
    }
}
