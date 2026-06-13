package com.example.designpatterns.creational.abstractfactory.enums;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {

    WELCOME_MESSAGE(Set.of(ChannelType.EMAIL, ChannelType.SMS, ChannelType.PUSH)), 
    
    PASSWORD_RESET(Set.of(ChannelType.EMAIL)), 
    
    PROMO_ALERT(Set.of(ChannelType.SMS, ChannelType.PUSH)),

    CHANGE_PIN(Set.of(ChannelType.EMAIL, ChannelType.SMS));

    private final Set<ChannelType> allowedChannels;

    public boolean supportsChannel(ChannelType channel) {
        return this.allowedChannels.contains(channel);
    }
}
