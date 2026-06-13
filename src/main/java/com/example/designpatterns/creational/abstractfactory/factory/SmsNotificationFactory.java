package com.example.designpatterns.creational.abstractfactory.factory;

import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.MessageFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.SmsFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.MessageSender;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.SmsSender;
import org.springframework.stereotype.Component;

@Component("sms")
public class SmsNotificationFactory implements NotificationFactory {

    @Override
    public MessageFormatter createFormatter() {
        return new SmsFormatter();
    }

    @Override
    public MessageSender createSender() {
        return new SmsSender();
    }
}
