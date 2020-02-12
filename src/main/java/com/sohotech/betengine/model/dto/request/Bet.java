package com.sohotech.betengine.model.dto.request;

import com.sohotech.betengine.model.entity.TeamEntity;

import java.io.Serializable;

public class Bet implements Serializable {
    private Team team;
    private long marketId;
    private double amount;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
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
