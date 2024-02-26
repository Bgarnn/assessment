package com.kbtg.bootcamp.posttest.Lottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lottery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lottery {
    @Id
    @NotNull(message = "cannot be null")
    @Size(min = 6, max = 6, message = "incorrect TicketId, must be 6 digits")
    @Pattern(regexp = "^[0-9]{6}$", message = "incorrect TicketId, non-integer")
    private String ticketId;

    @NotNull(message = "cannot be null")
    @Positive(message = "must be positive number")
    private Integer ticketAmount;

    @NotNull(message = "cannot be null")
    @PositiveOrZero(message = "must be positive number")
    private Double ticketPrice;
}
