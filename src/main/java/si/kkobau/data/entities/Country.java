package si.kkobau.data.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "COUNTRY")
public class Country extends PanacheEntity {

    @Enumerated(EnumType.STRING)
    private TaxationRule taxationRule;

    @OneToMany(mappedBy = "country")
    private List<Trader> traders;

    public TaxationRule getTaxationRule() {
        return taxationRule;
    }

    public void setTaxationRule(TaxationRule taxationRule) {
        this.taxationRule = taxationRule;
    }

    public List<Trader> getTraders() {
        return traders;
    }

    public void setTraders(List<Trader> traders) {
        this.traders = traders;
    }
}
