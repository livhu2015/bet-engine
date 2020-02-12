package com.sohotech.betengine.service;

import com.sohotech.betengine.model.dto.request.Bet;
import com.sohotech.betengine.model.dto.request.Team;
import com.sohotech.betengine.model.dto.response.BetResponse;
import com.sohotech.betengine.model.dto.response.Payout;
import org.springframework.stereotype.Service;

@Service
public interface BetManagementService {

    Payout play(Bet bet);
    BetResponse retrieveBet(long betId);
}
