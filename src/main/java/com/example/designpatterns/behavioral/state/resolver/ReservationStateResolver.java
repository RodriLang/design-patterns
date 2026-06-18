package com.example.designpatterns.behavioral.state.resolver;

import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import com.example.designpatterns.behavioral.state.states.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ReservationStateResolver {

    private final Map<ReservationStatus, ReservationState> states;

    public ReservationStateResolver(List<ReservationState> states) {
        this.states = states.stream()
                .collect(Collectors.toMap(
                        ReservationState::getStatus,
                        Function.identity()
                ));
    }

    public ReservationState resolve(ReservationStatus status) {
        return Optional.ofNullable(states.get(status))
                .orElseThrow(() -> new IllegalArgumentException("No state implementation found for " + status));

    }
}