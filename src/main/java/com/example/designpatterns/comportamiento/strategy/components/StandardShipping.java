package com.example.designpatterns.comportamiento.strategy.components;

import com.example.designpatterns.comportamiento.strategy.ShippingStrategy;
import org.springframework.stereotype.Component;

//nombrar el bean explicitamente usando el ENUM para que Spring los registre de forma ordenada
@Component("STANDARD")
public class StandardShipping implements ShippingStrategy {
    @Override
    public double calculateCost(double weight, double distance) {
        return (weight * 1.5) + (distance * 0.5);
    }
}
