package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OccupiedState implements ReservationState {

    @Override
    public ReservationStatus getStatus() {
        return ReservationStatus.OCCUPIED;
    }

    @Override
    public void confirm(Reservation reservation) {
        throw new InvalidStateTransitionException("Una reserva ocupada no puede volver a ocuparse.");
    }

    @Override
    public void markAsOccupied(Reservation reservation) {
        throw new InvalidStateTransitionException("La reserva ya se encuentra ocupada.");
    }

    @Override
    public void complete(Reservation reservation) {
        reservation.setStatus(ReservationStatus.COMPLETED);
    }

    @Override
    public void cancel(Reservation reservation) {
        throw new InvalidStateTransitionException("No se puede cancelar una reserva que ya se ha ocupado.");
    }

    @Override
    public BigDecimal calculateCancellationPenalty() {
        return BigDecimal.valueOf(5000);
    }

    @Override
    public String getNotificationMessage() {
        return "La mesa ya se encuentra ocupada por los clientes.";
    }
}