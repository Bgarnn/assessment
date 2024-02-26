package com.kbtg.bootcamp.posttest.UserTest;

import com.kbtg.bootcamp.posttest.User.UserController;
import com.kbtg.bootcamp.posttest.UserTicket.UserTicketResponseDto;
import com.kbtg.bootcamp.posttest.UserTicket.UserTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    UserTicketService userTicketService;

    @BeforeEach
    void setUp() {
        UserController userController = new UserController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("POST /users/:userId/lotteries/:ticketId should return id and 200 StatusOK")
    public void UserBuyLotteryApiSuccess() throws Exception {

        when(userTicketService.buyLottery("0123456789", "000001")).thenReturn("1");

        mockMvc.perform(post("/users/{userId}/lotteries/{ticketId}", "0123456789", "000001")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    @DisplayName("GET /users/:userId/lotteries should return all user tickets and 200 StatusOK")
    public void GetUserLotterySuccess() throws Exception {
        UserTicketResponseDto userTicketResponseDto = new UserTicketResponseDto(List.of("000001", "000002"), 2, 160.0);

        when(userTicketService.getUserTicketAll("0123456789")).thenReturn(userTicketResponseDto);

        mockMvc.perform(get("/users/{userId}/lotteries", "0123456789"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count", is(2)))
                .andExpect(jsonPath("$.cost", is(160.0)))
                .andExpect(jsonPath("$.tickets", is(List.of("000001","000002"))));
    }

    @Test
    @DisplayName("DELETE /users/:userId/lotteries/:ticketId should return deleted ticketId")
    void DeleteUserLotterySuccess() throws Exception {

        doNothing().when(userTicketService).deleteBuyTicket("0123456789", "000001");

        mockMvc.perform(delete("/users/{userId}/lotteries/{ticketId}", "0123456789", "000001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is("000001")));
    }
}
