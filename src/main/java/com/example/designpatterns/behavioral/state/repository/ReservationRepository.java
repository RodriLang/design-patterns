package com.example.designpatterns.behavioral.state.repository;

import com.example.designpatterns.behavioral.state.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
