package si.kkobau.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.resteasy.reactive.common.NotImplementedYet;
import si.kkobau.api.models.BetReturnInfoDto;
import si.kkobau.api.models.PlayDto;
import si.kkobau.data.entities.Trader;
import si.kkobau.data.repositories.TraderRepository;

@ApplicationScoped
public class BettingService {

    private final TraderRepository traderRepository;

    @Inject
    public BettingService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    public BetReturnInfoDto processPlay(PlayDto playDto) {
        Trader trader = traderRepository.findById(playDto.getTraderId());
        throw new NotImplementedYet();
    }
}
