package com.example.designpatterns.comportamiento.strategy.dto;

import com.example.designpatterns.comportamiento.strategy.ShippingType;
//con record java genera automaticamente constructores, getters, equals, hashCode y toString. Son final, no tienen setters y son inmutables. Son ideales para representar datos que no cambian después de su creación.
public record ShippingRequest(
        ShippingType type,
        double weight,
        double distance
) {}
