package com.sohotech.betengine.service.impl;

import com.sohotech.betengine.model.dto.request.Event;
import com.sohotech.betengine.model.dto.request.Market;

import com.sohotech.betengine.model.dto.response.EventResponse;
import com.sohotech.betengine.model.dto.response.MarketResponse;
import com.sohotech.betengine.model.entity.EventEntity;
import com.sohotech.betengine.model.entity.MarketEntity;
import com.sohotech.betengine.model.entity.OutcomeEntity;
import com.sohotech.betengine.repository.EventRepository;
import com.sohotech.betengine.repository.MarketRepository;
import com.sohotech.betengine.service.EventManagementService;
import com.sohotech.betengine.service.utility.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EventManagementServiceImpl extends SequenceGeneratorService implements EventManagementService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private MarketRepository marketRepository;
    
    @Override
    public void createEvent(Event event) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(generateSequence(EventEntity.SEQUENCE_NAME));
        eventEntity.setDescription(event.getDescription());
        eventEntity.setEndDateTime(event.getEndDateTime());
        eventEntity.setStartDateTime(event.getStartDateTime());
        eventRepository.save(eventEntity);
    }

    @Override
    public EventResponse retrieve(long eventId) {
        EventResponse response = new EventResponse();
        Optional<EventEntity> event = eventRepository.findById(eventId);
        if (!event.isPresent()) {
            return new EventResponse();
        }
        response.setDescription(event.get().getDescription());
        response.setEventId(event.get().getEventId());
        response.setStartDateTime(event.get().getStartDateTime());
        response.setEndDateTime(event.get().getEndDateTime());
        return response;
    }

    @Override
    public List<EventResponse> retrievesAll() {
        List<EventResponse> eventResponses = new ArrayList<>();
        EventResponse response = null;
        List<EventEntity> eventEntities =  eventRepository.findAll();
        for (EventEntity eventEntity: eventEntities) {
            response = new EventResponse();
            response.setDescription(eventEntity.getDescription());
            response.setEndDateTime(eventEntity.getEndDateTime());
            response.setEventId(eventEntity.getEventId());
            response.setStartDateTime(eventEntity.getStartDateTime());
            eventResponses.add(response);
        }
        return eventResponses;
    }

    @Override
    public MarketResponse createMarket(Market market, long eventId) {
        MarketResponse response = new MarketResponse();
        OutcomeEntity outcomeEntity = new OutcomeEntity();
        Optional<EventEntity> event = eventRepository.findById(eventId);
        MarketEntity marketEntity = new MarketEntity();
        marketEntity.setMarketId(generateSequence(MarketEntity.SEQUENCE_NAME));
        marketEntity.setDescription(market.getDescription());
        marketEntity.setTeamA(market.getTeamA());
        marketEntity.setTeamB(market.getTeamB());
        marketEntity.setOutcome(new OutcomeEntity());
        if (!event.isPresent()) {
            return new MarketResponse();
        }
        marketEntity.setEvent(event.get());
        marketRepository.save(marketEntity);
        return response;
    }

    @Override
    public List<MarketResponse> retrieveMarkets(long eventId) {
        List<MarketResponse> marketResponses = new ArrayList<>();
        List<MarketEntity> marketEntities = marketRepository.findAll();
        MarketResponse market = null;
        for (MarketEntity marketEntity: marketEntities) {
            if (marketEntity.getEvent().getEventId() == eventId) {
                market = new MarketResponse();
                market.setDescription(marketEntity.getDescription());
                market.setTeamA(marketEntity.getTeamA());
                market.setTeamB(marketEntity.getTeamB());
                market.setProbability(marketEntity.getProbability());
                marketResponses.add(market);
            }

        }

        return marketResponses;
    }

    @Override
    public MarketResponse retrieveMarket(long marketId) {
        MarketResponse response = new MarketResponse();

        Optional<MarketEntity> marketEntity = marketRepository.findById(marketId);
        if (marketEntity.isPresent()) {
            response.setProbability(marketEntity.get().getProbability());
            response.setTeamA(marketEntity.get().getTeamA());
            response.setTeamB(marketEntity.get().getTeamB());
            response.setDescription(marketEntity.get().getDescription());
        }
        return response;
    }

    @Override
    public MarketResponse updateMarketProbability(int probability, long marketId) {
        MarketResponse response = new MarketResponse();
        Optional<MarketEntity> marketEntity = marketRepository.findById(marketId);
        long eventId = marketEntity.get().getEvent().getEventId();
        Optional<EventEntity> eventEntity = eventRepository.findById(eventId);

        if (!marketEntity.isPresent()) {
            return new MarketResponse();
        }
        MarketEntity updatedMarket = new MarketEntity();
        updatedMarket.setMarketId(marketId);
        updatedMarket.setProbability(probability);
        updatedMarket.setOutcome(marketEntity.get().getOutcome());
        updatedMarket.setEvent(eventEntity.get());
        updatedMarket.setTeamB(marketEntity.get().getTeamB());
        updatedMarket.setTeamA(marketEntity.get().getTeamA());
        updatedMarket.setDescription(marketEntity.get().getDescription());

        MarketEntity updatedMarketEntity = marketRepository.save(updatedMarket);

        response.setTeamA(updatedMarketEntity.getTeamA());
        response.setTeamB(updatedMarketEntity.getTeamB());
        response.setDescription(updatedMarketEntity.getDescription());
        response.setProbability(updatedMarketEntity.getProbability());

        return response;
    }
}
