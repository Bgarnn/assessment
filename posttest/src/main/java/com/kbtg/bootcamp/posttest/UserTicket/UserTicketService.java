package com.kbtg.bootcamp.posttest.UserTicket;

import com.kbtg.bootcamp.posttest.Exception.NotFoundException;
import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import com.kbtg.bootcamp.posttest.Lottery.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTicketService {
    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;

    @Autowired
    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }


    public String buyLottery(String userId, String ticketId) {
        Optional<Lottery> ticketBuy = lotteryRepository.findByTicketId(ticketId);
        if (ticketBuy.isEmpty())
            throw new NotFoundException(ticketId + ": no ticket exist");

        UserTicket userTicket = new UserTicket(userId, ticketBuy.get().getTicketId(), ticketBuy.get().getTicketAmount(), ticketBuy.get().getTicketPrice(), ticketBuy.get());
        userTicketRepository.save(userTicket);

        return userTicket.getId().toString();
    }

    public UserTicketResponseDto getUserTicketAll(String userId) {
        List<String> ticketAll = userTicketRepository.findUserTicketIdsByUserId(userId);
        if (ticketAll.isEmpty())
            throw new NotFoundException(userId + ": no ticket exist");

        Integer count = userTicketRepository.sumUserTicketAmountByUserId(userId);
        Double cost = userTicketRepository.sumUserTicketPriceByUserId(userId);

        return new UserTicketResponseDto(ticketAll, count, cost);
    }

    public void deleteBuyTicket(String userId, String ticketId) {
        Optional<UserTicket> userTicket = userTicketRepository.findByUserIdAndUserTicketId(userId, ticketId);
        if (userTicket.isEmpty())
            throw new NotFoundException("Ticket: " + ticketId + "in User: " + userId + ": not found");

        userTicketRepository.delete(userTicket.get());
    }
}

