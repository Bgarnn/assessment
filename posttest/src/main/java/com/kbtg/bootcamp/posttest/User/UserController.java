package com.kbtg.bootcamp.posttest.User;

import com.kbtg.bootcamp.posttest.UserTicket.UserTicketResponseDto;
import com.kbtg.bootcamp.posttest.UserTicket.UserTicketService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserTicketService userticketService;

    public UserController(UserTicketService userticketService) {
        this.userticketService = userticketService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<Map<String, String>> buyLottery(
            @PathVariable @Pattern(regexp = "^[0-9]{10}$") String userId,
            @PathVariable @Pattern(regexp = "^[0-9]{6}$") String ticketId) {
        String id = userticketService.buyLottery(userId, ticketId);
        return new ResponseEntity<>(Map.of("id", id), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/lotteries")
    public ResponseEntity<UserTicketResponseDto> getUserTicket(
            @PathVariable @Pattern(regexp = "^[0-9]{10}$") String userId) {
        UserTicketResponseDto userTicket = userticketService.getUserTicketAll(userId);
        return ResponseEntity.ok(userTicket);
    }

    @DeleteMapping("/{userId}/lotteries/{ticketId}")
    public ResponseEntity<Map<String, String>> deleteUserTicket(
            @PathVariable @Pattern(regexp = "^[0-9]{10}$") String userId,
            @PathVariable @Pattern(regexp = "^[0-9]{6}$") String ticketId) {
        userticketService.deleteBuyTicket(userId, ticketId);
        return new ResponseEntity<>(Map.of("ticket", ticketId), HttpStatus.OK);
    }
}
