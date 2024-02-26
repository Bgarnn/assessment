package com.kbtg.bootcamp.posttest.UserTicket;

import com.kbtg.bootcamp.posttest.Lottery.Lottery;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_ticket")
@Getter
@Setter
@NoArgsConstructor
public class UserTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "cannot be null")
    @Size(min = 10, max = 10, message = "incorrect UserID, must be exactly 10 characters long")
    @Pattern(regexp = "^[0-9]{10}$", message = "UserID must consist of 10 digits")
    private String UserId;

    @NotNull(message = "cannot be null")
    private String UserTicketId;

    @NotNull(message = "cannot be null")
    private Integer UserTicketAmount;
    @NotNull(message = "cannot be null")
    private Double UserTicketPrice;

    @OneToOne
    private Lottery Lottery;

    public UserTicket(String userId, String userTicketId, Integer userTicketAmount,
                      Double userTicketPrice, com.kbtg.bootcamp.posttest.Lottery.Lottery lottery) {
        UserId = userId;
        UserTicketId = userTicketId;
        UserTicketAmount = userTicketAmount;
        UserTicketPrice = userTicketPrice;
        Lottery = lottery;
    }
}

