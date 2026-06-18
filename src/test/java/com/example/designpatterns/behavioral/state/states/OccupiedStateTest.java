package com.example.designpatterns.behavioral.state.states;

import com.example.designpatterns.behavioral.state.exception.InvalidStateTransitionException;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OccupiedStateTest {

    private final OccupiedState state = new OccupiedState();

    @Test
    void complete_shouldChangeStatusToCompleted() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.OCCUPIED)
                .build();

        state.complete(reservation);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.COMPLETED);
    }

    @Test
    void confirm_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.OCCUPIED)
                .build();

        assertThatThrownBy(() -> state.confirm(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void occupy_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.OCCUPIED)
                .build();

        assertThatThrownBy(() -> state.markAsOccupied(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void cancel_shouldThrowException() {
        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.OCCUPIED)
                .build();

        assertThatThrownBy(() -> state.cancel(reservation))
                .isInstanceOf(InvalidStateTransitionException.class);
    }

    @Test
    void calculateCancellationPenalty_shouldReturnFiveThousand() {
        assertThat(state.calculateCancellationPenalty())
                .isEqualByComparingTo(BigDecimal.valueOf(5000));
    }

    @Test
    void getStatus_shouldReturnOccupied() {
        assertThat(state.getStatus()).isEqualTo(ReservationStatus.OCCUPIED);
    }

    @Test
    void getNotificationMessage_shouldReturnExpectedMessage() {
        assertThat(state.getNotificationMessage())
                .isEqualTo("La mesa ya se encuentra ocupada por los clientes.");
    }
}