package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;

import java.math.BigDecimal;

public class CancelledState implements ReservationState {

    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.CANCELLED;
    }

    @Override
    public void confirm(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva cancelada no puede confirmarse.");
    }

    @Override
    public void markAsOccupied(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva cancelada no puede ocuparse.");
    }

    @Override
    public void complete(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva cancelada no puede finalizarse.");
    }

    @Override
    public void cancel(Reservation reservation) {
        throw new InvalidStateTransitionException("La reserva ya se encuentra cancelada.");
    }

    @Override
    public BigDecimal calculateCancellationPenalty() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getNotificationMessage() {
        return "La reserva fue cancelada.";
    }
}
