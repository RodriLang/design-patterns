package com.example.designpatterns.comportamiento.strategy.service;

import com.example.designpatterns.comportamiento.strategy.ShippingStrategy;
import com.example.designpatterns.comportamiento.strategy.ShippingType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShippingService {

    //Spring inyecta automáticamente un Map con los beans de tipo ShippingStrategy, donde la clave es el nombre del bean (definido por el ENUM) y el valor es la instancia del bean
    private final Map<String, ShippingStrategy> strategies;

    public double calculateShippingCost(ShippingType type, double weight, double distance) {
        return Optional.ofNullable(strategies.get(type.name()))
                .map(strategie -> strategie.calculateCost(weight, distance))
                .orElseThrow(() -> new IllegalArgumentException("No se encontro una estrategia de envio implementada para: " + type
                ));
    }


}
