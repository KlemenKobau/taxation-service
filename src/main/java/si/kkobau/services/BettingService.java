package si.kkobau.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import si.kkobau.data.repositories.TraderRepository;

@ApplicationScoped
public class BettingService {

    private final TraderRepository traderRepository;

    @Inject
    public BettingService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }
}
