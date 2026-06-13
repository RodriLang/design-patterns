package com.example.designpatterns.creational.abstractfactory.dto.request;

import com.example.designpatterns.creational.abstractfactory.enums.ChannelType;
import com.example.designpatterns.creational.abstractfactory.enums.EventType;
import java.util.UUID;

public record NotificationRequest(

        UUID userId,
        EventType eventType,
        ChannelType channel
) {
}
