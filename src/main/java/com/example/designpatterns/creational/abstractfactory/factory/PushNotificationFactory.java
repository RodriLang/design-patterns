package com.example.designpatterns.creational.abstractfactory.factory;

import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.MessageFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.PushFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.MessageSender;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.PushSender;
import org.springframework.stereotype.Component;

@Component("push")
public class PushNotificationFactory implements NotificationFactory {

    @Override
    public MessageFormatter createFormatter() {
        return new PushFormatter();
    }

    @Override
    public MessageSender createSender() {
        return new PushSender();
    }
}
