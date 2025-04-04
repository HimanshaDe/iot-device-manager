package com.iot.iot_device_manager.repository;

import com.iot.iot_device_manager.model.IoTDevice;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class IoTDeviceInMemoryRepository {
    private final Map<Integer, IoTDevice> deviceMap = new ConcurrentHashMap<>();
    private int idCounter = 1;
    public List<IoTDevice> findAll(){
        return new ArrayList<>(deviceMap.values());
    }

    public IoTDevice findById(Integer id){
        return deviceMap.get(id);
    }

    public void save(IoTDevice ioTDevice){
        ioTDevice.setId(idCounter++);
        deviceMap.put(ioTDevice.getId(), ioTDevice);
    }

    public IoTDevice update(IoTDevice ioTDevice){
    IoTDevice existingDevice = deviceMap.get(ioTDevice.getId());
    if(existingDevice != null){
        existingDevice.setName(ioTDevice.getName());
        existingDevice.setStatus(ioTDevice.getStatus());
        existingDevice.setType(ioTDevice.getType());
        existingDevice.setLastCommunication(LocalDateTime.now());
        deviceMap.put(ioTDevice.getId(),existingDevice);
        return existingDevice;
    }else{
        return null;
    }

    }
    public void delete(String id){
        deviceMap.remove(id);
    }

    public boolean existsById(Integer id){
        return deviceMap.containsKey(id);
    }

}
