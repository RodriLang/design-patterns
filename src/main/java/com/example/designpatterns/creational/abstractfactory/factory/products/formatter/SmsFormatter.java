package com.example.designpatterns.creational.abstractfactory.factory.products.formatter;

public class SmsFormatter implements MessageFormatter {

    @Override
    public String format(String eventType) {
        return switch (eventType) {
            case "WELCOME_MESSAGE" -> "¡Bienvenido a nuestra plataforma! Gracias por registrarte.";
            case "PROMO_ALERT" -> "¡Oferta especial!Aprovecha un 20% de descuento hoy.";
            case "CHANGE_PIN" -> "Tu nuevo pin es 1234.";
            default -> throw new IllegalArgumentException("There is no SMS text message set up for the event: " +  eventType);
        };
    }
}
