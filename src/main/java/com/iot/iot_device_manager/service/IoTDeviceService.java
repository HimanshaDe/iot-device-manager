package com.iot.iot_device_manager.service;

import com.iot.iot_device_manager.dto.IoTDeviceRequestDto;
import com.iot.iot_device_manager.dto.ResponseDTO;

public interface IoTDeviceService {
    ResponseDTO getDeviceById(Integer id);

    ResponseDTO createDevice(IoTDeviceRequestDto ioTDeviceRequestDto);
}
