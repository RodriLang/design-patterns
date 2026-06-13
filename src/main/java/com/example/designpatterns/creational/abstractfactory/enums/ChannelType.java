package com.example.designpatterns.creational.abstractfactory.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelType {

    EMAIL("email"),
    PUSH("push"),
    SMS("sms"),
    OTHER( "other");

    private final String channelName;
}
