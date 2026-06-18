package com.example.designpatterns.behavioral.state.resolver;

import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import com.example.designpatterns.behavioral.state.states.CancelledState;
import com.example.designpatterns.behavioral.state.states.CompletedState;
import com.example.designpatterns.behavioral.state.states.ConfirmedState;
import com.example.designpatterns.behavioral.state.states.OccupiedState;
import com.example.designpatterns.behavioral.state.states.RequestedState;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ReservationStateResolverTest {

    @Test
    void resolve_shouldReturnCorrectStateImplementation() {
        ReservationStateResolver resolver = new ReservationStateResolver(List.of(
                new RequestedState(),
                new ConfirmedState(),
                new OccupiedState(),
                new CompletedState(),
                new CancelledState()
        ));

        assertThat(resolver.resolve(ReservationStatus.REQUESTED))
                .isInstanceOf(RequestedState.class);

        assertThat(resolver.resolve(ReservationStatus.CONFIRMED))
                .isInstanceOf(ConfirmedState.class);

        assertThat(resolver.resolve(ReservationStatus.OCCUPIED))
                .isInstanceOf(OccupiedState.class);

        assertThat(resolver.resolve(ReservationStatus.COMPLETED))
                .isInstanceOf(CompletedState.class);

        assertThat(resolver.resolve(ReservationStatus.CANCELLED))
                .isInstanceOf(CancelledState.class);
    }
}