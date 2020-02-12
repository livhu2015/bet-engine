package com.sohotech.betengine.model.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "markets")
public class MarketEntity {
    @Transient
    public static final String SEQUENCE_NAME = "markets_sequence";
    @Id
    private long marketId;
    private double probability;

    @DBRef
    private EventEntity event;

    private String description;
    private String teamA;
    private String teamB;

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }


    @DBRef
    private OutcomeEntity outcome;
    public OutcomeEntity getOutcome() {
        return outcome;
    }

    public void setOutcome(OutcomeEntity outcome) {
        this.outcome = outcome;
    }

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public long getMarketId() {
        return marketId;
    }

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }
}
