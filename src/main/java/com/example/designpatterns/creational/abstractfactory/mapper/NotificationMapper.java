package com.example.designpatterns.creational.abstractfactory.mapper;

import com.example.designpatterns.creational.abstractfactory.dto.response.NotificationResponse;
import com.example.designpatterns.creational.abstractfactory.model.Notification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationResponse toDto(Notification entity, String message);
}
