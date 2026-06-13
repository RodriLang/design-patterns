package com.example.designpatterns.creational.abstractfactory.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String status;

    private Integer code;

    private String message;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
