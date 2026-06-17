package com.example.designpatterns.comportamiento.strategy;

//define una interfaz común para todas las estrategias de envío
@FunctionalInterface
public interface ShippingStrategy {
    double calculateCost(double weight, double distance);
}
