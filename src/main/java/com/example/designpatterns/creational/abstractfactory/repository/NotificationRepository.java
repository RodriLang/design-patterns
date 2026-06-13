package com.example.designpatterns.creational.abstractfactory.repository;

import com.example.designpatterns.creational.abstractfactory.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
