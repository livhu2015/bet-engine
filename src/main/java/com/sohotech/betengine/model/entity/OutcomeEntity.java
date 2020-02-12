package com.sohotech.betengine.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "outcome")
public class OutcomeEntity {
    @Transient
    public static final String SEQUENCE_NAME = "outcome_sequence";
    @Id
    private long outcomeId;
    @DBRef
    private Collection<MarketEntity> markets;

    private boolean win;
    private boolean loss;
    private boolean draw;

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public Collection<MarketEntity> getMarkets() {
        return markets;
    }

    public void setMarkets(Collection<MarketEntity> markets) {
        this.markets = markets;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean isLoss() {
        return loss;
    }

    public void setLoss(boolean loss) {
        this.loss = loss;
    }

    public boolean isDraw() {
        return draw;
    }

    public void setDraw(boolean draw) {
        this.draw = draw;
    }

}
