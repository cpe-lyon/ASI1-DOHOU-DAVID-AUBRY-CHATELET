package cpe.atelier3.commons.market.dto;

import cpe.atelier3.commons.market.MarketBuyRequest;
import org.springframework.stereotype.Component;

@Component
public class MarketBuyRequestDtoMapper {

    public MarketBuyRequest marketBuyRequestDtoToMarketBuyRequest(MarketBuyRequestDTO marketBuyRequestDTO) {
        return new MarketBuyRequest(marketBuyRequestDTO.userId(), marketBuyRequestDTO.marketOfferId());
    }
}
