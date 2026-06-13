package com.example.designpatterns.creational.abstractfactory.service.impl;

import com.example.designpatterns.creational.abstractfactory.dto.request.NotificationRequest;
import com.example.designpatterns.creational.abstractfactory.dto.response.NotificationResponse;
import com.example.designpatterns.creational.abstractfactory.enums.StatusType;
import com.example.designpatterns.creational.abstractfactory.factory.NotificationFactory;
import com.example.designpatterns.creational.abstractfactory.factory.NotificationFactoryProvider;
import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.MessageFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.MessageSender;
import com.example.designpatterns.creational.abstractfactory.mapper.NotificationMapper;
import com.example.designpatterns.creational.abstractfactory.model.Notification;
import com.example.designpatterns.creational.abstractfactory.repository.NotificationRepository;
import com.example.designpatterns.creational.abstractfactory.service.NotificationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final NotificationMapper mapper;
    private final NotificationFactoryProvider notificationFactoryProvider;

    @Override
    @Transactional
    public NotificationResponse processNotification(NotificationRequest request) {

        if (!request.eventType().supportsChannel(request.channel())) {
            throw new IllegalArgumentException(
                    String.format("The event %s is not allowed to be sent through the %s channel.",
                            request.eventType().name(), request.channel().name())
            );
        }

        NotificationFactory factory = notificationFactoryProvider.getFactory(request.channel());

        if (factory == null) {
            throw new IllegalArgumentException("Unsupported channel: " + request.channel().getChannelName());
        }

        MessageFormatter formatter = factory.createFormatter();
        MessageSender sender = factory.createSender();

        String content = formatter.format(request.eventType().name());
        sender.send(request.userId().toString(), content);

        Notification entity = Notification.builder()
                .userId(request.userId())
                .channel(request.channel())
                .eventType(request.eventType())
                .status(StatusType.SUCCESS)
                .messageContent(content)
                .build();

        Notification savedEntity = repository.save(entity);

        return mapper.toDto(savedEntity, "Notification sent successfully");
    }
}