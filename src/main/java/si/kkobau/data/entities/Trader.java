package si.kkobau.data.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "traders")
public class Trader extends PanacheEntity {

}
