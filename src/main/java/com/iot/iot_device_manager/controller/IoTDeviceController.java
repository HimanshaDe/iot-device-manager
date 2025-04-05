package com.iot.iot_device_manager.controller;


import com.iot.iot_device_manager.dto.IoTDeviceRequestDto;
import com.iot.iot_device_manager.dto.ResponseDTO;
import com.iot.iot_device_manager.service.IoTDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/devices")
@Slf4j
public class IoTDeviceController {

    @Autowired
    private IoTDeviceService ioTDeviceService;

    /*
     * Api to create a new device
     * @param ioTDeviceRequestDto
     * @return responseDTO
     * */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createDevice(@RequestBody IoTDeviceRequestDto ioTDeviceRequestDto){
        ResponseDTO responseDTO = ioTDeviceService.createDevice(ioTDeviceRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /*
     * Api to get a device data by id
     * @param - device id
     * @return responseDTO
     * */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getDeviceById(@PathVariable Integer id){
        ResponseDTO responseDTO = new ResponseDTO();
        if (responseDTO.getStatus() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        responseDTO = ioTDeviceService.getDeviceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /*
     * Api to get device list
     * @param - device id
     * @return responseDTO
     * */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getDevices(){
        ResponseDTO responseDTO = new ResponseDTO();
        if (responseDTO.getStatus() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        responseDTO = ioTDeviceService.getDevices();
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /*
     * Api to update a device
     * @param device id and ioTDeviceRequestDto
     * @return responseDTO
     * */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateDevice(@RequestBody IoTDeviceRequestDto ioTDeviceRequestDto, @PathVariable Integer id){
        ResponseDTO responseDTO = ioTDeviceService.updateDevice(ioTDeviceRequestDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    /*
     * Api to delete a device
     * @param device id
     * @return responseDTO
     * */

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteDevice(@PathVariable Integer id){
        ResponseDTO responseDTO = new ResponseDTO();
        if (responseDTO.getStatus() == HttpStatus.NOT_FOUND.value()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
        responseDTO = ioTDeviceService.deleteDevice(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

}
