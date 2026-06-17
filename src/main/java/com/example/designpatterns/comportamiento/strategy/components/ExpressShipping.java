package com.example.designpatterns.comportamiento.strategy.components;

import com.example.designpatterns.comportamiento.strategy.ShippingStrategy;
import org.springframework.stereotype.Component;

@Component("EXPRESS")
public class ExpressShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        final double baseCost = 10.0; // Costo base fijo para envíos express
        return (weight * 3.0) + (distance * 1.2) + baseCost;
    }
}
