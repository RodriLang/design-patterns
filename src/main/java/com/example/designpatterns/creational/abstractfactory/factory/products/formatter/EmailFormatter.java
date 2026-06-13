package com.example.designpatterns.creational.abstractfactory.factory.products.formatter;

public class EmailFormatter implements MessageFormatter {

    @Override
    public String format(String eventType) {
        return switch (eventType) {
            case "WELCOME_MESSAGE" -> "<h1>¡Bienvenido a nuestra plataforma!</h1><p>Gracias por registrarte.</p>";
            case "PASSWORD_RESET" -> "<h1>Recuperación de cuenta</h1><p>Hacé clic acá para resetear tu clave.</p>";
            case "PROMO_ALERT" -> "<h1>¡Oferta especial!</h1><p>Aprovecha un 20% de descuento hoy.</p>";
            case "CHANGE_PIN" -> "<h1>No pierdas acceso a tu app</h1><p>Tu nuevo pin es 1234</p>";
            default -> throw new IllegalArgumentException("There is no email template configured for the event: " + eventType);
        };
    }
}
