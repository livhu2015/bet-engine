package com.sohotech.betengine.controller;

import com.sohotech.betengine.model.dto.request.Event;
import com.sohotech.betengine.model.dto.request.Market;
import com.sohotech.betengine.model.dto.response.EventResponse;
import com.sohotech.betengine.model.dto.response.MarketResponse;
import com.sohotech.betengine.service.EventManagementService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventManagementService eventManagementService;

    @GetMapping("/ping")
    String ping() {
        return "pong!";
    }

    @PostMapping("/create")
    public void createEvent(Event event) {
        eventManagementService.createEvent(event);
    }

    @GetMapping("/retrieve/{eventId}")
    public EventResponse retrieveEvent(@PathVariable("eventId") long eventId) {
        return eventManagementService.retrieve(eventId);
    }

    @GetMapping("/all")
    public List<EventResponse> allEvents() {
        return eventManagementService.retrievesAll();
    }

    @PostMapping("{eventId}/create-market")
    public MarketResponse createMarket(@RequestBody Market market, @PathVariable("eventId") long eventId) {
        return eventManagementService.createMarket(market, eventId);
    }

    @GetMapping("/{eventId}/markets")
    public List<MarketResponse> retrieveMarkets(@PathVariable("eventId") long eventId) {
        return eventManagementService.retrieveMarkets(eventId);
    }

    @PutMapping("/market/{marketId}/update/{probability}")
    public MarketResponse updateMarketProbability(@PathVariable("probability") int probability, @PathVariable("marketId") long marketId) {

        return eventManagementService.updateMarketProbability(probability, marketId);
    }

    @GetMapping("/market/{marketId}")
    public MarketResponse retrieveMarket(@PathVariable("marketId") long marketId) {
        return eventManagementService.retrieveMarket(marketId);
    }

}
