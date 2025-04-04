package com.iot.iot_device_manager.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IoTDeviceRequestDto {
    private String name;
    private String type;
    private Boolean status;
    private LocalDateTime lastCommunication;
}
