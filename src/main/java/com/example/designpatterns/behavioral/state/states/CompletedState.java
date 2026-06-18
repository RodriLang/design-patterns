package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;

import java.math.BigDecimal;

public class CompletedState implements ReservationState {

    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.COMPLETED;
    }

    @Override
    public void confirm(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva finalizada no puede confirmarse.");
    }

    @Override
    public void markAsOccupied(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva finalizada no puede ocuparse.");
    }

    @Override
    public void complete(Reservation reservation) {
        throw new InvalidStateTransitionException("La reserva ya se encuentra finalizada.");
    }

    @Override
    public void cancel(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva finalizada no puede cancelarse.");
    }

    @Override
    public BigDecimal calculateCancellationPenalty() {
        return BigDecimal.ZERO;
    }

    @Override
    public String getNotificationMessage() {
        return "La reserva fue finalizada correctamente.";
    }
}
