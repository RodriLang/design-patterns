package com.example.designpatterns.behavioral.state.service.impl;

import com.example.designpatterns.behavioral.state.dto.request.CreateReservationRequest;
import com.example.designpatterns.behavioral.state.dto.response.ReservationResponse;
import com.example.designpatterns.behavioral.state.mapper.ReservationMapper;
import com.example.designpatterns.behavioral.state.model.Reservation;
import com.example.designpatterns.behavioral.state.model.ReservationStatus;
import com.example.designpatterns.behavioral.state.repository.ReservationRepository;
import com.example.designpatterns.behavioral.state.resolver.ReservationStateResolver;
import com.example.designpatterns.behavioral.state.states.ReservationState;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @Mock
    private ReservationStateResolver stateResolver;

    @Mock
    private ReservationState reservationState;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Test
    void create_shouldMapRequestSetRequestedStatusSaveAndReturnResponse() {

        CreateReservationRequest request = new CreateReservationRequest(
                "Juan Pérez",
                4,
                LocalDateTime.of(2026, 6, 13, 21, 0)
        );

        Reservation reservation = Reservation.builder()
                .customerName("Juan Pérez")
                .numberOfGuests(4)
                .reservationDateTime(LocalDateTime.of(2026, 6, 13, 21, 0))
                .build();

        Reservation savedReservation = Reservation.builder()
                .id(1L)
                .customerName("Juan Pérez")
                .numberOfGuests(4)
                .reservationDateTime(LocalDateTime.of(2026, 6, 13, 21, 0))
                .status(ReservationStatus.REQUESTED)
                .build();

        ReservationResponse response = new ReservationResponse(
                1L,
                "Juan Pérez",
                4,
                LocalDateTime.of(2026, 6, 13, 21, 0),
                ReservationStatus.REQUESTED
        );

        when(reservationMapper.toEntity(request))
                .thenReturn(reservation);

        when(reservationRepository.save(reservation))
                .thenReturn(savedReservation);

        when(reservationMapper.toDto(savedReservation))
                .thenReturn(response);

        ReservationResponse result =
                reservationService.create(request);

        verify(reservationMapper).toEntity(request);

        assertThat(reservation.getStatus())
                .isEqualTo(ReservationStatus.REQUESTED);

        verify(reservationRepository).save(reservation);

        verify(reservationMapper).toDto(savedReservation);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void confirm_shouldDelegateToCurrentStateSaveReservationAndReturnResponse() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .status(ReservationStatus.REQUESTED)
                .build();

        ReservationResponse response = new ReservationResponse(
                1L,
                "Juan Pérez",
                4,
                LocalDateTime.of(2026, 6, 13, 21, 0),
                ReservationStatus.CONFIRMED
        );

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(stateResolver.resolve(ReservationStatus.REQUESTED)).thenReturn(reservationState);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(response);

        ReservationResponse result = reservationService.confirm(1L);

        verify(reservationState).confirm(reservation);
        verify(reservationRepository).save(reservation);
        verify(reservationMapper).toDto(reservation);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void occupy_shouldDelegateToCurrentStateSaveReservationAndReturnResponse() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .status(ReservationStatus.CONFIRMED)
                .build();

        ReservationResponse response = new ReservationResponse(
                1L,
                "Juan Pérez",
                4,
                LocalDateTime.of(2026, 6, 13, 21, 0),
                ReservationStatus.OCCUPIED
        );

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(stateResolver.resolve(ReservationStatus.CONFIRMED)).thenReturn(reservationState);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(response);

        ReservationResponse result = reservationService.occupy(1L);

        verify(reservationState).markAsOccupied(reservation);
        verify(reservationRepository).save(reservation);
        verify(reservationMapper).toDto(reservation);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void complete_shouldDelegateToCurrentStateSaveReservationAndReturnResponse() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .status(ReservationStatus.OCCUPIED)
                .build();

        ReservationResponse response = new ReservationResponse(
                1L,
                "Juan Pérez",
                4,
                LocalDateTime.of(2026, 6, 13, 21, 0),
                ReservationStatus.COMPLETED
        );

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(stateResolver.resolve(ReservationStatus.OCCUPIED)).thenReturn(reservationState);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(response);

        ReservationResponse result = reservationService.complete(1L);

        verify(reservationState).complete(reservation);
        verify(reservationRepository).save(reservation);
        verify(reservationMapper).toDto(reservation);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void cancel_shouldDelegateToCurrentStateSaveReservationAndReturnResponse() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .status(ReservationStatus.CONFIRMED)
                .build();

        ReservationResponse response = new ReservationResponse(
                1L,
                "Juan Pérez",
                4,
                LocalDateTime.of(2026, 6, 13, 21, 0),
                ReservationStatus.CANCELLED
        );

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(stateResolver.resolve(ReservationStatus.CONFIRMED)).thenReturn(reservationState);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(response);

        ReservationResponse result = reservationService.cancel(1L);

        verify(reservationState).cancel(reservation);
        verify(reservationRepository).save(reservation);
        verify(reservationMapper).toDto(reservation);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void calculateCancellationPenalty_shouldDelegateToCurrentState() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .status(ReservationStatus.CONFIRMED)
                .build();

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(stateResolver.resolve(ReservationStatus.CONFIRMED)).thenReturn(reservationState);
        when(reservationState.calculateCancellationPenalty())
                .thenReturn(BigDecimal.valueOf(1000));

        BigDecimal result = reservationService.calculateCancellationPenalty(1L);

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(1000));

        verify(reservationState).calculateCancellationPenalty();
        verify(reservationRepository, never()).save(any());
        verifyNoInteractions(reservationMapper);
    }

    @Test
    void getNotificationMessage_shouldDelegateToCurrentState() {
        Reservation reservation = Reservation.builder()
                .id(1L)
                .status(ReservationStatus.REQUESTED)
                .build();

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(stateResolver.resolve(ReservationStatus.REQUESTED)).thenReturn(reservationState);
        when(reservationState.getNotificationMessage())
                .thenReturn("La solicitud de reserva fue registrada correctamente.");

        String result = reservationService.getNotificationMessage(1L);

        assertThat(result)
                .isEqualTo("La solicitud de reserva fue registrada correctamente.");

        verify(reservationState).getNotificationMessage();
        verify(reservationRepository, never()).save(any());
        verifyNoInteractions(reservationMapper);
    }

    @Test
    void confirm_shouldThrowExceptionWhenReservationDoesNotExist() {
        when(reservationRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reservationService.confirm(99L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Reservation not found");

        verifyNoInteractions(stateResolver);
        verifyNoInteractions(reservationMapper);
        verify(reservationRepository, never()).save(any());
    }
}