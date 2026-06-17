package com.example.designpatterns.comportamiento.strategy.controller;

import com.example.designpatterns.comportamiento.strategy.dto.ShippingRequest;
import com.example.designpatterns.comportamiento.strategy.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculateCost(@RequestBody ShippingRequest request) {
        double cost = shippingService.calculateShippingCost(
                request.type(),
                request.weight(),
                request.distance()
        );

        return ResponseEntity.ok(cost);
    }
}
