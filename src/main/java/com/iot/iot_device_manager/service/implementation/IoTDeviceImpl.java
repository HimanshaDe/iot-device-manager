package com.iot.iot_device_manager.service.implementation;

import com.iot.iot_device_manager.dto.ResponseDTO;
import com.iot.iot_device_manager.model.IoTDevice;
import com.iot.iot_device_manager.repository.IoTDeviceInMemoryRepository;
import com.iot.iot_device_manager.service.IoTDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@Service
@Slf4j
public class IoTDeviceImpl implements IoTDeviceService {

    @Autowired
    private IoTDeviceInMemoryRepository ioTDeviceInMemoryRepository;

    /*
     * Api to get a device data by id
     * @param - device id
     * @return responseDTO
     * */
    @Override
    public ResponseDTO getDeviceById(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();

     try{
         IoTDevice ioTDevice = ioTDeviceInMemoryRepository.findById(id);
         if(ioTDevice == null){
             responseDTO.setMessage("Iot device not found");
             responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
         }else {
             responseDTO.setData(ioTDevice);
             responseDTO.setMessage("Iot device found");
             responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
         }
         responseDTO.setTimestamp(LocalDateTime.now());
         return responseDTO;

     }catch (MethodArgumentTypeMismatchException e){
         log.error("Error occurred :" + "Device id invalid");
         responseDTO.setMessage("Error occurred :" + "Device id invalid");
         responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseDTO.setTimestamp(LocalDateTime.now());
         return responseDTO;

     }catch (Exception e){
         log.error("Error occurred ");
         responseDTO.setMessage(e.getMessage());
         responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseDTO.setTimestamp(LocalDateTime.now());
         return responseDTO;
     }
    }
}
