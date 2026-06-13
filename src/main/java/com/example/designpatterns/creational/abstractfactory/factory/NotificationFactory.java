package com.example.designpatterns.creational.abstractfactory.factory;

import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.MessageFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.MessageSender;

public interface NotificationFactory {

    MessageFormatter createFormatter();

    MessageSender createSender();

}
