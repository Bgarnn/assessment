package com.kbtg.bootcamp.posttest.LotteryTest;

import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LotteryTest {
    @Test
    @DisplayName("add correct ticketID to Lottery class")
    void LotteryClassAddTicketIdSuccess() {
        Lottery lottery = new Lottery();

        lottery.setTicketId("123456");

        assertEquals("123456", lottery.getTicketId());
    }

    @Test
    @DisplayName("add correct ticketAmount to Lottery class")
    void LotteryClassAddTicketAmountSuccess() {
        Lottery lottery = new Lottery();

        lottery.setTicketAmount(1);

        assertEquals(1, lottery.getTicketAmount());
    }

    @Test
    @DisplayName("add correct ticketPrice to Lottery class")
    void LotteryClassAddTicketPriceSuccess() {
        Lottery lottery = new Lottery();

        lottery.setTicketPrice(80.0);

        assertEquals(80.0, lottery.getTicketPrice());
    }

    @Test
    @DisplayName("successfully create Lottery class with all args constructor")
    void LotteryClassSuccess() {
        Lottery lottery = new Lottery("000001", 1, 80.0);

        assertEquals("000001", lottery.getTicketId());
        assertEquals(1, lottery.getTicketAmount());
        assertEquals(80.0, lottery.getTicketPrice());
    }
}
