package com.example.designpatterns.behavioral.state.dto.response;

import com.example.designpatterns.behavioral.state.model.ReservationStatus;

import java.time.LocalDateTime;

public record ReservationResponse(

        Long id,
        String customerName,
        Integer numberOfGuests,
        LocalDateTime reservationDateTime,
        ReservationStatus status
) {
}
