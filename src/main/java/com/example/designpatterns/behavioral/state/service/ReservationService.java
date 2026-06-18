package com.example.designpatterns.behavioral.state.service;

import com.example.designpatterns.behavioral.state.dto.request.CreateReservationRequest;
import com.example.designpatterns.behavioral.state.dto.response.ReservationResponse;

import java.math.BigDecimal;

public interface ReservationService {

    ReservationResponse create(CreateReservationRequest request);

    ReservationResponse confirm(Long reservationId);

    ReservationResponse occupy(Long reservationId);

    ReservationResponse complete(Long reservationId);

    ReservationResponse cancel(Long reservationId);

    BigDecimal calculateCancellationPenalty(Long reservationId);

    String getNotificationMessage(Long reservationId);

}