package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;

import java.math.BigDecimal;

public interface ReservationState {

    ReservationStatus getStatus();

    void confirm(Reservation reservation);

    void markAsOccupied(Reservation reservation);

    void complete(Reservation reservation);

    void cancel(Reservation reservation);

    BigDecimal calculateCancellationPenalty();

    String getNotificationMessage();
}
