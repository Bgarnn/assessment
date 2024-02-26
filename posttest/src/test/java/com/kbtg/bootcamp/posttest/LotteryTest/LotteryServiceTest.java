package com.kbtg.bootcamp.posttest.LotteryTest;

import com.kbtg.bootcamp.posttest.Exception.BadRequestException;
import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import com.kbtg.bootcamp.posttest.Lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.Lottery.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.Lottery.LotteryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class LotteryServiceTest {
    @Mock
    private LotteryRepository lotteryRepository;
    @InjectMocks
    private LotteryService lotteryService;

    @Test
    @DisplayName("find all ticket in Lottery table ")
    void findAllTicket() {

        List<String> TicketAll = lotteryService.getAllTicketId();

        assertEquals(lotteryRepository.findTicketIdAll(), TicketAll);
    }

    @Test
    @DisplayName("Create lottery success should return ticketId")
    public void CreateLotteryMethodSuccess() {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", 1, 80.0);

        when(lotteryRepository.findByTicketId("000001")).thenReturn(java.util.Optional.empty());
        String ticketId = lotteryService.createLottery(lotteryRequestDto);

        assertEquals("000001", ticketId);
        verify(lotteryRepository).findByTicketId("000001");
        verify(lotteryRepository).save(any(Lottery.class));
    }

    @Test
    @DisplayName("Create lottery that already exists should throw BadRequestException")
    public void CreateExistLotteryMethod() {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", 1, 80.0);

        when(lotteryRepository.findByTicketId("000001")).thenReturn(java.util.Optional.of(new Lottery()));

        assertThrows(BadRequestException.class, () -> lotteryService.createLottery(lotteryRequestDto));
        verify(lotteryRepository).findByTicketId("000001");
        verify(lotteryRepository, never()).save(any());
    }
}