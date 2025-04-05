package com.iot.iot_device_manager.service;

import com.iot.iot_device_manager.dto.IoTDeviceRequestDto;
import com.iot.iot_device_manager.dto.ResponseDTO;

public interface IoTDeviceService {

    ResponseDTO createDevice(IoTDeviceRequestDto ioTDeviceRequestDto);

    ResponseDTO getDeviceById(Integer id);

    ResponseDTO updateDevice(IoTDeviceRequestDto ioTDeviceRequestDto, Integer id);

    ResponseDTO deleteDevice(Integer id);

    ResponseDTO getDevices();
}
