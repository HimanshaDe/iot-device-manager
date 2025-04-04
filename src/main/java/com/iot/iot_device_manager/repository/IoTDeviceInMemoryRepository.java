package com.iot.iot_device_manager.repository;

import com.iot.iot_device_manager.model.IoTDevice;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class IoTDeviceInMemoryRepository {
    private final Map<Integer, IoTDevice> deviceMap = new ConcurrentHashMap<>();

    public List<IoTDevice> findAll(){
        return new ArrayList<>(deviceMap.values());
    }

    public IoTDevice findById(Integer id){
        return deviceMap.get(id);
    }

    public void save(IoTDevice ioTDevice){
        deviceMap.put(ioTDevice.getId(), ioTDevice);
    }

    public void delete(String id){
        deviceMap.remove(id);
    }

    public boolean existsById(Integer id){
        return deviceMap.containsKey(id);
    }

}
