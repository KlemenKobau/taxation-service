package si.kkobau.data.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.kkobau.data.entities.Trader;

@ApplicationScoped
public class TraderRepository implements PanacheRepository<Trader> {
}
