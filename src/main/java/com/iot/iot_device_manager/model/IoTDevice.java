package com.iot.iot_device_manager.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IoTDevice {
    private int id;
    private String name;
    private String type;
    private Boolean status;
    private LocalDateTime lastCommunication;
}
