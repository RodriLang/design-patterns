package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ConfirmedStateTest {

    private final ConfirmedState state = new ConfirmedState();

    @Test
    void occupy_shouldChangeStatusToOccupied() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.CONFIRMED)
                .build();

        state.markAsOccupied(reservation);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.OCCUPIED);
    }

    @Test
    void cancel_shouldChangeStatusToCancelled() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.CONFIRMED)
                .build();

        state.cancel(reservation);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCELLED);
    }

    @Test
    void confirm_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.CONFIRMED)
                .build();

        assertThatThrownBy(() -> state.confirm(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void complete_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.CONFIRMED)
                .build();

        assertThatThrownBy(() -> state.complete(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void calculateCancellationPenalty_shouldReturnOneThousand() {
        assertThat(state.calculateCancellationPenalty())
                .isEqualByComparingTo(BigDecimal.valueOf(1000));
    }

    @Test
    void getStatus_shouldReturnConfirmed() {
        assertThat(state.getStatus()).isEqualTo(ReservationStatus.CONFIRMED);
    }

    @Test
    void getNotificationMessage_shouldReturnExpectedMessage() {
        assertThat(state.getNotificationMessage())
                .isEqualTo("La reserva fue confirmada. Te esperamos en el horario indicado.");
    }
}