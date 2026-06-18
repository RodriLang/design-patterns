package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestedState implements ReservationState {

    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.REQUESTED;
    }

    @Override
    public void confirm(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CONFIRMED);
    }

    @Override
    public void markAsOccupied(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva solicitada no puede ocuparse sin estar confirmada.");
    }

    @Override
    public void complete(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva solicitada no puede finalizarse.");
    }

    @Override
    public void cancel(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CANCELLED);
    }

    @Override
    public BigDecimal calculateCancellationPenalty() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getNotificationMessage() {
        return "La solicitud de reserva fue registrada correctamente.";
    }
}