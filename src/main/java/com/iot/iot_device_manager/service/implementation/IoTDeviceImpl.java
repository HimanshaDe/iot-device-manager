package com.iot.iot_device_manager.service.implementation;

import com.iot.iot_device_manager.dto.IoTDeviceRequestDto;
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
import java.util.List;

@Service
@Slf4j
public class IoTDeviceImpl implements IoTDeviceService {

    @Autowired
    private IoTDeviceInMemoryRepository ioTDeviceInMemoryRepository;

    /*
     * Get Device By id method implementation
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

     }catch (Exception e){
         log.error("Error occurred ");
         responseDTO.setMessage(e.getMessage());
         responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
         responseDTO.setTimestamp(LocalDateTime.now());
         return responseDTO;
     }
    }

    /*
     * Create Device method implementation
     * @param null
     * @return responseDTO
     * */
    @Override
    public ResponseDTO createDevice(IoTDeviceRequestDto ioTDeviceRequestDto) {
        ResponseDTO responseDTO = new ResponseDTO();

        try{
            IoTDevice ioTDevice = new IoTDevice();
            ioTDevice.setName(ioTDeviceRequestDto.getName());
            ioTDevice.setStatus(ioTDeviceRequestDto.getStatus());
            ioTDevice.setType(ioTDeviceRequestDto.getType());
            ioTDevice.setLastCommunication(ioTDeviceRequestDto.getLastCommunication());
            ioTDeviceInMemoryRepository.save(ioTDevice);

            responseDTO.setData(ioTDevice);
            responseDTO.setMessage("Iot device created Successfully");
            responseDTO.setStatus(HttpStatus.CREATED.value());
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

    /*
     * Update device method implementation
     * @param device id and ioTDeviceRequestDto
     * @return responseDTO
     * */
    @Override
    public ResponseDTO updateDevice(IoTDeviceRequestDto ioTDeviceRequestDto, Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();

        try{
            if(!ioTDeviceInMemoryRepository.existsById(id)){
                responseDTO.setMessage("Iot device not found");
                responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            }else{
                IoTDevice ioTDevice = new IoTDevice();
                ioTDevice.setId(id);
                ioTDevice.setName(ioTDeviceRequestDto.getName());
                ioTDevice.setStatus(ioTDeviceRequestDto.getStatus());
                ioTDevice.setType(ioTDeviceRequestDto.getType());
                ioTDevice.setLastCommunication(ioTDeviceRequestDto.getLastCommunication());

                IoTDevice updatedDevice = ioTDeviceInMemoryRepository.update(ioTDevice);

                responseDTO.setStatus(HttpStatus.OK.value());
                responseDTO.setMessage("Device updated successfully");
                responseDTO.setData(updatedDevice);
                return responseDTO;

            }
        }catch (Exception e){
            log.error("Error occurred ");
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseDTO.setTimestamp(LocalDateTime.now());
            return responseDTO;
        }
        return responseDTO;
    }

    /*
     * Delete a device method implementation
     * @param device id and ioTDeviceRequestDto
     * @return responseDTO
     * */
    @Override
    public ResponseDTO deleteDevice(Integer id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            IoTDevice ioTDevice = ioTDeviceInMemoryRepository.findById(id);
            if(ioTDevice == null){
                responseDTO.setMessage("Iot device not found");
                responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            }else {
                ioTDeviceInMemoryRepository.delete(id);
                responseDTO.setData(null);
                responseDTO.setMessage("Iot device deleted");
                responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            }
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

    @Override
    public ResponseDTO getDevices() {
        ResponseDTO responseDTO = new ResponseDTO();
        try{
            List<IoTDevice> ioTDevices = ioTDeviceInMemoryRepository.findAll();
            if(ioTDevices.isEmpty()){
                responseDTO.setMessage("Iot device not found");
                responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            }else {
                responseDTO.setData(ioTDevices);
                responseDTO.setMessage("Iot devices retrieved successfully");
                responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
            }
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
