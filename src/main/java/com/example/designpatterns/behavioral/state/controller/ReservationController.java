package com.example.designpatterns.behavioral.state.controller;

import com.example.designpatterns.behavioral.state.dto.request.CreateReservationRequest;
import com.example.designpatterns.behavioral.state.dto.response.ReservationResponse;
import com.example.designpatterns.behavioral.state.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@RequestBody CreateReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.create(request));
    }

    @PatchMapping("/{id}/confirm")
    public ResponseEntity<ReservationResponse> confirm(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.confirm(id));
    }

    @PatchMapping("/{id}/occupy")
    public ResponseEntity<ReservationResponse> occupy(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.occupy(id));
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<ReservationResponse> complete(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.complete(id));
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ReservationResponse> cancel(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.cancel(id));
    }

    @GetMapping("/{id}/penalty")
    public ResponseEntity<BigDecimal> calculateCancellationPenalty(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.calculateCancellationPenalty(id));
    }

    @GetMapping("/{id}/notification")
    public ResponseEntity<String> getNotificationMessage(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getNotificationMessage(id));
    }
}