package com.iot.iot_device_manager.exception;

import com.iot.iot_device_manager.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
@ControllerAdvice
public class DeviceException {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDTO> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Error occurred" +ex.getMessage());
        responseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
    }
}
