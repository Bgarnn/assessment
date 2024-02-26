package com.kbtg.bootcamp.posttest.Lottery;

import jakarta.validation.constraints.*;

public record LotteryRequestDto(
        @NotNull(message = "cannot be null")
        @Size(min = 6, max = 6, message = "must be 6 digits")
        @Pattern(regexp = "^[0-9]{6}$", message = "must be number")
        String ticket,
        @NotNull(message = "cannot be null")
        @Positive(message = "must be positive number")
        Integer amount,
        @NotNull(message = "cannot be null")
        @PositiveOrZero(message = "must be positive number")
        Double price) {
}
