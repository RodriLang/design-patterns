package com.example.designpatterns.behavioral.state.mapper;

import com.example.designpatterns.behavioral.state.dto.request.CreateReservationRequest;
import com.example.designpatterns.behavioral.state.dto.response.ReservationResponse;
import com.example.designpatterns.behavioral.state.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    ReservationResponse toDto (Reservation entity);

    Reservation toEntity (CreateReservationRequest dto);
}
