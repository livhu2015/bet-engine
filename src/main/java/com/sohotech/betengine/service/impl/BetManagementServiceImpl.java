package com.sohotech.betengine.service.impl;

import com.sohotech.betengine.model.dto.request.Bet;
import com.sohotech.betengine.model.dto.request.Team;
import com.sohotech.betengine.model.dto.response.BetResponse;
import com.sohotech.betengine.model.dto.response.Payout;
import com.sohotech.betengine.model.entity.BetEntity;
import com.sohotech.betengine.model.entity.MarketEntity;
import com.sohotech.betengine.model.entity.TeamEntity;
import com.sohotech.betengine.repository.BetRepository;
import com.sohotech.betengine.repository.MarketRepository;
import com.sohotech.betengine.service.BetManagementService;
import com.sohotech.betengine.service.utility.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
public class BetManagementServiceImpl extends SequenceGeneratorService implements BetManagementService {

    private static double CHANCES = 0.3;
    private static int WIN = 1;
    private static int DRAW = 0;
    private static int LOSE = -1;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private BetRepository betRepository;

    @Override
    public Payout play(Bet bet) {

        Team team = bet.getTeam();
        Payout payout = new Payout();

        Optional<MarketEntity> market = marketRepository.findById(bet.getMarketId());
        int gameResults = team.getGameResult();

        if (!market.isPresent()) {
            return new Payout();
        }
        BetEntity betEntity = new BetEntity();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        betEntity.setAmount(bet.getAmount());
        betEntity.setTeam(teamEntity);
        betEntity.setBetId(generateSequence(BetEntity.SEQUENCE_NAME));

        //store the bet to the database
        betRepository.save(betEntity);

        //which is randomized from input selection
        double marketProbability = market.get().getProbability();
        if ( marketProbability <= 0) {
            return new Payout();
        }
        double odds = calculateOddsProbability(marketProbability);
        double paycheck = 0;

        if (odds > CHANCES && gameResults == WIN) {
            payout.setOutcome("win");
            paycheck = new BigDecimal(bet.getAmount() * odds).setScale(2, RoundingMode.HALF_UP).doubleValue();

        } else if(odds == CHANCES && gameResults == DRAW) {
            payout.setOutcome("draw");
            paycheck = new BigDecimal(bet.getAmount() * odds).setScale(2, RoundingMode.HALF_UP).doubleValue();

        } else if (odds < CHANCES && gameResults == LOSE) {
            payout.setOutcome("lose");
            paycheck = new BigDecimal(bet.getAmount() * odds).setScale(2, RoundingMode.HALF_UP).doubleValue();

        }

        payout.setAmount(paycheck);
        return payout;

    }

    @Override
    public BetResponse retrieveBet(long betId) {
        BetResponse betResponse = new BetResponse();
        BetEntity betEntity = betRepository.findById(betId).get();
        betResponse.setAmount(betEntity.getAmount());
        betResponse.setTeam(betEntity.getTeam().getName());

        return betResponse;
    }

    double calculateOddsProbability(double probability) {
        return 1/probability;
    }

}
