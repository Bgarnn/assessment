package com.kbtg.bootcamp.posttest.AdminTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbtg.bootcamp.posttest.Admin.AdminController;
import com.kbtg.bootcamp.posttest.Lottery.LotteryRequestDto;
import com.kbtg.bootcamp.posttest.Lottery.LotteryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {
    private MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        AdminController adminController = new AdminController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("POST /admin/lotteries success should return ticketId and 201 StatusCreated")
    void CreateLotteryApiSuccess() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", 1, 80.0);

        when(lotteryService.createLottery(lotteryRequestDto)).thenReturn("000001");

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ticket").value("000001"));
    }

    @Test
    @DisplayName("POST /admin/lotteries with null ticketId should return BadRequestException")
    void CreateLotteryNullTicketId() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto(null, 1, 80.0);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /admin/lotteries with too long ticketId should return BadRequestException")
    void CreateLotteryLongTicketId() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("1234567", 1, 80.0);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /admin/lotteries with non-digit ticketId should return BadRequestException")
    void CreateLotteryNonDigitTicketId() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("0000xx", 1, 80.0);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /admin/lotteries with null ticketAmount should return BadRequestException")
    void CreateLotteryNullTicketAmount() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", null, 80.0);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /admin/lotteries with negative ticketAmount should return BadRequestException")
    void CreateLotteryNegativeTicketAmount() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", -1, 80.0);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /admin/lotteries with null ticketPrice should return BadRequestException")
    void CreateLotteryNullTicketPrice() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", 1, null);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /admin/lotteries with negative ticketPrice should return BadRequestException")
    void CreateLotteryNegativeTicketPrice() throws Exception {
        LotteryRequestDto lotteryRequestDto = new LotteryRequestDto("000001", 1, -80.0);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(lotteryRequestDto)))
                .andExpect(status().isBadRequest());
    }
}
