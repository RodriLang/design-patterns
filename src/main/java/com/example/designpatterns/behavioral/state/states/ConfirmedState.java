package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;

import java.math.BigDecimal;

public class ConfirmedState implements ReservationState {

    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.CONFIRMED;
    }

    @Override
    public void confirm(Reservation reservation) {
        throw new InvalidStateTransitionException("La reserva ya se encuentra confirmada.");
    }

    @Override
    public void markAsOccupied(Reservation reservation) {
        reservation.setStatus(ReservationStatus.OCCUPIED);
    }

    @Override
    public void complete(Reservation reservation) {
        throw new InvalidStateTransitionException("No se puede completar una reserva que no ha iniciado.");

    }

    @Override
    public void cancel(Reservation reservation) {
        reservation.setStatus(ReservationStatus.CANCELLED);
    }

    @Override
    public BigDecimal calculateCancellationPenalty() {
        return BigDecimal.valueOf(1000);
    }

    @Override
    public String getNotificationMessage() {
        return "La reserva fue confirmada. Te esperamos en el horario indicado.";
    }
}
