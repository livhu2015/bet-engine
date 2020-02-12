package com.sohotech.betengine.service;

import com.sohotech.betengine.model.dto.request.Event;
import com.sohotech.betengine.model.dto.request.Market;
import com.sohotech.betengine.model.dto.response.EventResponse;
import com.sohotech.betengine.model.dto.response.MarketResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventManagementService {
    void createEvent(Event event);
    EventResponse retrieve(long eventId);
    List<EventResponse> retrievesAll();
    MarketResponse createMarket(Market market, long eventId);
    List<MarketResponse> retrieveMarkets(long eventId);
    MarketResponse retrieveMarket(long marketId);
    MarketResponse updateMarketProbability(int probability, long marketId);
}
