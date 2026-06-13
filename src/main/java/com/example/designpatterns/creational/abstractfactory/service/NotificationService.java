package com.example.designpatterns.creational.abstractfactory.service;

import com.example.designpatterns.creational.abstractfactory.dto.request.NotificationRequest;
import com.example.designpatterns.creational.abstractfactory.dto.response.NotificationResponse;

public interface NotificationService {

    NotificationResponse processNotification(NotificationRequest request);

}
