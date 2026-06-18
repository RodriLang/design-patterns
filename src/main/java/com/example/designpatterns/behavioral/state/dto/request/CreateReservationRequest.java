package com.example.designpatterns.behavioral.state.dto.request;

import java.time.LocalDateTime;

public record CreateReservationRequest(
        String customerName,
        Integer numberOfGuests,
        LocalDateTime reservationDateTime
) {
}
