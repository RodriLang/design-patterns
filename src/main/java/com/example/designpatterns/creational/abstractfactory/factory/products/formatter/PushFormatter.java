package com.example.designpatterns.creational.abstractfactory.factory.products.formatter;

public class PushFormatter implements MessageFormatter {

    @Override
    public String format(String eventType) {
        return switch (eventType) {
            case "WELCOME_MESSAGE" -> "¡Bienvenido a nuestra plataforma! Gracias por registrarte.";
            case "PROMO_ALERT" -> "¡Oferta especial!Aprovecha un 20% de descuento hoy.";
            default -> throw new IllegalArgumentException("There is no Push Notification configured for the event: " + eventType);
        };
    }
}
