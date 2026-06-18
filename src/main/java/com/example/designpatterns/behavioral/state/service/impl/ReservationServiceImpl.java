package com.example.designpatterns.behavioral.state.service.impl;

import com.example.designpatterns.behavioral.state.dto.request.CreateReservationRequest;
import com.example.designpatterns.behavioral.state.dto.response.ReservationResponse;
import com.example.designpatterns.behavioral.state.mapper.ReservationMapper;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import com.example.designpatterns.behavioral.state.repository.ReservationRepository;
import com.example.designpatterns.behavioral.state.resolver.ReservationStateResolver;
import com.example.designpatterns.behavioral.state.service.ReservationService;
import com.example.designpatterns.behavioral.state.states.ReservationState;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final ReservationStateResolver stateResolver;

    @Override
    public ReservationResponse create(CreateReservationRequest request) {
        Reservation reservation = reservationMapper.toEntity(request);
        reservation.setStatus(ReservationStatus.REQUESTED);

        return this.save(reservation);
    }

    @Override
    public ReservationResponse confirm(Long reservationId) {
        Reservation reservation = findById(reservationId);
        ReservationState state = stateResolver.resolve(reservation.getStatus());

        state.confirm(reservation);

        return this.save(reservation);
    }

    @Override
    public ReservationResponse occupy(Long reservationId) {
        Reservation reservation = findById(reservationId);
        ReservationState state = stateResolver.resolve(reservation.getStatus());

        state.markAsOccupied(reservation);

        return this.save(reservation);
    }

    @Override
    public ReservationResponse complete(Long reservationId) {
        Reservation reservation = findById(reservationId);
        ReservationState state = stateResolver.resolve(reservation.getStatus());

        state.complete(reservation);

        return this.save(reservation);
    }

    @Override
    public ReservationResponse cancel(Long reservationId) {
        Reservation reservation = findById(reservationId);
        ReservationState state = stateResolver.resolve(reservation.getStatus());

        state.cancel(reservation);

        return this.save(reservation);
    }

    @Override
    public BigDecimal calculateCancellationPenalty(Long reservationId) {
        Reservation reservation = findById(reservationId);
        ReservationState state = stateResolver.resolve(reservation.getStatus());

        return state.calculateCancellationPenalty();
    }

    @Override
    public String getNotificationMessage(Long reservationId) {
        Reservation reservation = findById(reservationId);
        ReservationState state = stateResolver.resolve(reservation.getStatus());

        return state.getNotificationMessage();
    }

    private Reservation findById(Long reservationId) {
        return reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }

    private ReservationResponse save(Reservation reservation) {
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toDto(savedReservation);
    }
}
