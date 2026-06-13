package com.example.designpatterns.creational.abstractfactory.factory;

import com.example.designpatterns.creational.abstractfactory.enums.ChannelType;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationFactoryProvider {

    private final Map<String, NotificationFactory> factories;

    public NotificationFactory getFactory(ChannelType channel) {
        NotificationFactory factory = factories.get(channel.getChannelName());

        if (factory == null) {
            throw new IllegalArgumentException(
                    "Unsupported notification channel: " + channel.getChannelName());
        }

        return factory;
    }
}