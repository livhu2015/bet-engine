package com.sohotech.betengine.model.dto.response;

import com.sohotech.betengine.model.entity.TeamEntity;

public class BetResponse {
    private long betId;
    private String team;
    private long marketId;
    private double amount;

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
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
