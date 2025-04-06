package com.iot.iot_device_manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IoTDevice {
    private int id;
    private String name;
    private String type;
    private Boolean status;
    private LocalDateTime lastCommunication;


}
