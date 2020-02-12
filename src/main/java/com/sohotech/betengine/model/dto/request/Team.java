package com.sohotech.betengine.model.dto.request;

public class Team {
    private String teamName;
    private int gameResult;

    public String getName() {
        return teamName;
    }

    public void setName(String name) {
        this.teamName = name;
    }

    public int getGameResult() {
        return gameResult;
    }

    public void setGameResult(int gameResult) {
        this.gameResult = gameResult;
    }
}
