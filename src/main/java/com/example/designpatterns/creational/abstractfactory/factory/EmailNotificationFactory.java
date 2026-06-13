package com.example.designpatterns.creational.abstractfactory.factory;

import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.EmailFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.MessageFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.EmailSender;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.MessageSender;
import org.springframework.stereotype.Component;

@Component("email")
public class EmailNotificationFactory implements NotificationFactory {

    @Override
    public MessageFormatter createFormatter() {
        return new EmailFormatter();
    }

    @Override
    public MessageSender createSender() {
        return new EmailSender();
    }
}
