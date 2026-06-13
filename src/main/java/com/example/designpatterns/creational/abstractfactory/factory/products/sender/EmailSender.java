package com.example.designpatterns.creational.abstractfactory.factory.products.sender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailSender implements MessageSender{

    @Override
    public void send(String userId, String content) {
        log.info("Enviando vía SMTP a {}: {}", userId, content);
    }
}
