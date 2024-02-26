package com.kbtg.bootcamp.posttest.UserTicketTest;

import com.kbtg.bootcamp.posttest.UserTicket.UserTicket;
import com.kbtg.bootcamp.posttest.Exception.NotFoundException;
import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import com.kbtg.bootcamp.posttest.Lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.UserTicket.UserTicketRepository;
import com.kbtg.bootcamp.posttest.UserTicket.UserTicketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class UserTicketServiceTest {
    @Mock
    private LotteryRepository lotteryRepository;
    @Mock
    private UserTicketRepository userticketRepository;
    @InjectMocks
    private UserTicketService userTicketService;

    @Test
    @DisplayName("Buy non-exist ticket from lottery table should return NotFoundException")
    public void UserBuyNonExistLottery() {
        Lottery lottery = new Lottery();
        lottery.setTicketId("000001");

        when(lotteryRepository.findByTicketId("000001")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userTicketService.buyLottery("0123456789", "000001"));
        verify(userticketRepository, never()).findByUserIdAndUserTicketId("0123456789", "000001");
        verify(userticketRepository, never()).save(any(UserTicket.class));
    }

    @Test
    @DisplayName("Get non-existing ticket from UserTicket table should return NotFoundException")
    public void UserGetNonExistLottery() {

        when(userticketRepository.findUserTicketIdsByUserId("0123456789")).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> userTicketService.getUserTicketAll("0123456789"));
    }

    @Test
    @DisplayName("Delete UserTicket success")
    public void UserDeleteLotterySuccess() {
        UserTicket mockUserTicket = new UserTicket();
        when(userticketRepository.findByUserIdAndUserTicketId(eq("0123456789"), eq("000001"))).thenReturn(Optional.of(mockUserTicket));

        userTicketService.deleteBuyTicket("0123456789", "000001");

        verify(userticketRepository, times(1)).delete(eq(mockUserTicket));
    }
}