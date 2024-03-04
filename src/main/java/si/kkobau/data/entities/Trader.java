package si.kkobau.data.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "TRADER")
public class Trader extends PanacheEntityBase {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="COUNTRY_ID", nullable=false)
    private Country country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
