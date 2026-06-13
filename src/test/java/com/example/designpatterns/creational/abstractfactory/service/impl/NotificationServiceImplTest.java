package com.example.designpatterns.creational.abstractfactory.service.impl;

import java.util.UUID;

import com.example.designpatterns.creational.abstractfactory.dto.request.NotificationRequest;
import com.example.designpatterns.creational.abstractfactory.dto.response.NotificationResponse;
import com.example.designpatterns.creational.abstractfactory.enums.ChannelType;
import com.example.designpatterns.creational.abstractfactory.enums.EventType;
import com.example.designpatterns.creational.abstractfactory.factory.NotificationFactory;
import com.example.designpatterns.creational.abstractfactory.factory.products.formatter.MessageFormatter;
import com.example.designpatterns.creational.abstractfactory.factory.products.sender.MessageSender;
import com.example.designpatterns.creational.abstractfactory.mapper.NotificationMapper;
import com.example.designpatterns.creational.abstractfactory.model.Notification;
import com.example.designpatterns.creational.abstractfactory.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository repository;

    @Mock
    private NotificationMapper mapper;

    @Mock
    private NotificationFactory mockFactory;

    @Mock
    private MessageFormatter mockFormatter;

    @Mock
    private MessageSender mockSender;

    @InjectMocks
    private NotificationServiceImpl service;

    @BeforeEach
    void setUp() {
        Map<String, NotificationFactory> factoryMap = new HashMap<>();
        factoryMap.put("email", mockFactory);

        service = new NotificationServiceImpl(repository, mapper, factoryMap);
    }

    @Test
    void processNotification_Success() {
        UUID uuid = UUID.randomUUID();
        NotificationRequest request = new NotificationRequest(uuid, EventType.WELCOME_MESSAGE, ChannelType.EMAIL);

        when(mockFactory.createFormatter()).thenReturn(mockFormatter);
        when(mockFactory.createSender()).thenReturn(mockSender);
        when(mockFormatter.format("WELCOME_MESSAGE")).thenReturn("Mocked Content");

        Notification savedEntity = new Notification();
        when(repository.save(any(Notification.class))).thenReturn(savedEntity);

        NotificationResponse expectedResponse = new NotificationResponse("SUCCESS", "OK");
        when(mapper.toDto(eq(savedEntity), anyString())).thenReturn(expectedResponse);

        NotificationResponse response = service.processNotification(request);

        assertNotNull(response);
        assertEquals("SUCCESS", response.status());

        verify(mockFactory, times(1)).createFormatter();
        verify(mockFactory, times(1)).createSender();
        verify(mockSender, times(1)).send(uuid.toString(), "Mocked Content");
        verify(repository, times(1)).save(any(Notification.class));
    }

    @Test
    void processNotification_ThrowsExceptionWhenChannelNotSupported() {
        UUID uuid = UUID.randomUUID();
        NotificationRequest request = new NotificationRequest(uuid, EventType.CHANGE_PIN, ChannelType.PUSH);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> service.processNotification(request)
        );

        assertEquals("The event CHANGE_PIN is not allowed to be sent through the PUSH channel.", exception.getMessage());
        verifyNoInteractions(repository);
    }
}