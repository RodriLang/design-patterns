package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RequestedStateTest {

    private final RequestedState state = new RequestedState();

    @Test
    void confirm_shouldChangeStatusToConfirmed() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.REQUESTED)
                .build();

        state.confirm(reservation);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CONFIRMED);
    }

    @Test
    void cancel_shouldChangeStatusToCancelled() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.REQUESTED)
                .build();

        state.cancel(reservation);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCELLED);
    }

    @Test
    void occupy_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.REQUESTED)
                .build();

        assertThatThrownBy(() -> state.markAsOccupied(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void complete_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.REQUESTED)
                .build();

        assertThatThrownBy(() -> state.complete(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void calculateCancellationPenalty_shouldReturnZero() {
        assertThat(state.calculateCancellationPenalty())
                .isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void getStatus_shouldReturnRequested() {
        assertThat(state.getStatus()).isEqualTo(ReservationStatus.REQUESTED);
    }

    @Test
    void getNotificationMessage_shouldReturnExpectedMessage() {
        assertThat(state.getNotificationMessage())
                .isEqualTo("La solicitud de reserva fue registrada correctamente.");
    }
}