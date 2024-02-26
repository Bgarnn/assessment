package com.kbtg.bootcamp.posttest.UserTicketTest;

import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import com.kbtg.bootcamp.posttest.UserTicket.UserTicket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTicketTest {
    @Test
    @DisplayName("add correct userID to UserTicket class")
    void UserTicketClassAddUserIdSuccess() {
        UserTicket userTicket = new UserTicket();

        userTicket.setUserId("0123456789");

        assertEquals("0123456789", userTicket.getUserId());
    }

    @Test
    @DisplayName("add correct userTicketID to UserTicket class")
    void UserTicketClassAddUserTicketIdSuccess() {
        UserTicket userTicket = new UserTicket();

        userTicket.setUserTicketId("000001");

        assertEquals("000001", userTicket.getUserTicketId());
    }

    @Test
    @DisplayName("add correct userTicketAmount to UserTicket class")
    void UserTicketClassAddUserTicketAmountSuccess() {
        UserTicket userTicket = new UserTicket();

        userTicket.setUserTicketAmount(1);

        assertEquals(1, userTicket.getUserTicketAmount());
    }

    @Test
    @DisplayName("add correct userTicketPrice to UserTicket class")
    void UserTicketClassAddUserTicketPriceSuccess() {
        UserTicket userTicket = new UserTicket();

        userTicket.setUserTicketPrice(80.0);

        assertEquals(80.0, userTicket.getUserTicketPrice());
    }

    @Test
    @DisplayName("add correct id to UserTicket class")
    void UserTicketClassAddIdSuccess() {
        UserTicket userTicket = new UserTicket();

        userTicket.setId(1);

        assertEquals(1, userTicket.getId());
    }

    @Test
    @DisplayName("successfully create Lottery class with args constructor")
    void UserTicketClass() {
        Lottery lottery = new Lottery("000001", 1, 80.0);
        UserTicket userTicket = new UserTicket("0123456789", "000002", 1, 80.0, lottery);

        assertEquals("0123456789", userTicket.getUserId());
        assertEquals("000002", userTicket.getUserTicketId());
        assertEquals(1, userTicket.getUserTicketAmount());
        assertEquals(80.0, userTicket.getUserTicketPrice());
        assertEquals(lottery, userTicket.getLottery());
    }
}
