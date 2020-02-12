package com.sohotech.betengine.model.entity;

import com.sohotech.betengine.model.dto.request.Team;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bet")
public class BetEntity {
    @Transient
    public static final String SEQUENCE_NAME = "bets_sequence";
    @Id
    private long betId;
    @DBRef
    private TeamEntity team;
    private long marketId;
    private double amount;

    public static String getSequenceName() {
        return SEQUENCE_NAME;
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public TeamEntity getTeam() {
        return team;
    }

    public void setTeam(TeamEntity team) {
        this.team = team;
    }

    public long getMarketId() {
        return marketId;
    }

    public void setMarketId(long marketId) {
        this.marketId = marketId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
