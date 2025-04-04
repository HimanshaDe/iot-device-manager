package com.iot.iot_device_manager.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseDTO {
    private int status;
    private Object data;
    private String message;
    private LocalDateTime timestamp;

}
