package com.kbtg.bootcamp.posttest.Lottery;

import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public List<String> getAllTicketId() {
        return lotteryRepository.findTicketIdAll();
    }

    public String createLottery(LotteryRequestDto requestTicket) {
        if (lotteryRepository.findByTicketId(requestTicket.ticket()).isPresent())
            throw new BadRequestException(requestTicket.ticket() + " has already exist");
        Lottery TicketNew = new Lottery(requestTicket.ticket(), requestTicket.amount(), requestTicket.price());
        this.lotteryRepository.save(TicketNew);
        return TicketNew.getTicketId();
    }
}
