package com.sohotech.betengine.controller;

import com.sohotech.betengine.model.dto.request.Bet;
import com.sohotech.betengine.model.dto.response.BetResponse;
import com.sohotech.betengine.model.dto.response.Payout;
import com.sohotech.betengine.service.BetManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bet")
public class BetController {

    @Autowired
    private BetManagementService betManagementService;

    @PostMapping("/betting")
    public Payout gamble(@RequestBody Bet bet) {
        return betManagementService.play(bet);

    }

    @GetMapping("/{betId}")
    public BetResponse retrieveBet(@PathVariable("betId") long betId) {
        return betManagementService.retrieveBet(betId);
    }

}
