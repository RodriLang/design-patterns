package com.example.designpatterns.comportamiento.strategy.components;

import com.example.designpatterns.comportamiento.strategy.ShippingStrategy;
import org.springframework.stereotype.Component;

@Component("INTERNATIONAL")
public class InternationalShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        final double internationalSurcharge = 15.0; // Recargo fijo para envíos internacionales
        return (weight * 2.5) + (distance * 0.8) + internationalSurcharge;
    }
}
