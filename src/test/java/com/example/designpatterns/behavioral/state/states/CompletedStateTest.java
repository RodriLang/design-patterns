package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CompletedStateTest {

    private final CompletedState state = new CompletedState();

    @Test
    void confirm_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.COMPLETED)
                .build();

        assertThatThrownBy(() -> state.confirm(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void occupy_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.COMPLETED)
                .build();

        assertThatThrownBy(() -> state.markAsOccupied(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void complete_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.COMPLETED)
                .build();

        assertThatThrownBy(() -> state.complete(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void cancel_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.COMPLETED)
                .build();

        assertThatThrownBy(() -> state.cancel(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void calculateCancellationPenalty_shouldReturnZero() {
        assertThat(state.calculateCancellationPenalty())
                .isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void getStatus_shouldReturnCompleted() {
        assertThat(state.getStatus())
                .isEqualTo(ReservationStatus.COMPLETED);
    }

    @Test
    void getNotificationMessage_shouldReturnExpectedMessage() {
        assertThat(state.getNotificationMessage())
                .isEqualTo("La reserva fue finalizada correctamente.");
    }
}