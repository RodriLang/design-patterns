package com.example.designpatterns.creational.abstractfactory.controller;

import com.example.designpatterns.creational.abstractfactory.dto.request.NotificationRequest;
import com.example.designpatterns.creational.abstractfactory.dto.response.NotificationResponse;
import com.example.designpatterns.creational.abstractfactory.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.processNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
