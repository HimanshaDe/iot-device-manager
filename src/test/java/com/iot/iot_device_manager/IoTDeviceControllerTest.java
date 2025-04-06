package com.iot.iot_device_manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iot.iot_device_manager.controller.IoTDeviceController;
import com.iot.iot_device_manager.dto.IoTDeviceRequestDto;
import com.iot.iot_device_manager.dto.ResponseDTO;
import com.iot.iot_device_manager.model.IoTDevice;
import com.iot.iot_device_manager.repository.IoTDeviceInMemoryRepository;
import com.iot.iot_device_manager.service.IoTDeviceService;
import com.iot.iot_device_manager.service.implementation.IoTDeviceImpl;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IoTDeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IoTDeviceService ioTDeviceService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateDevice() throws Exception {
        IoTDeviceRequestDto requestDto = new IoTDeviceRequestDto();
        requestDto.setName("Device 1");
        requestDto.setStatus(true);
        requestDto.setType("Temperature Sensor");
        requestDto.setLastCommunication(LocalDateTime.now());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus(201);
        responseDTO.setMessage("Iot device created Successfully");
        responseDTO.setData(requestDto);
        responseDTO.setTimestamp(LocalDateTime.now());

        when(ioTDeviceService.createDevice(any(IoTDeviceRequestDto.class)))
                .thenReturn(responseDTO);

        ResultActions result = mockMvc.perform(post("/api/devices/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("201"))
                .andExpect(jsonPath("$.message").value("Iot device created Successfully"));
    }

    @Test
    void testGetDeviceById() throws Exception {

        IoTDeviceInMemoryRepository mockRepo = Mockito.mock(IoTDeviceInMemoryRepository.class);

        IoTDeviceImpl service = new IoTDeviceImpl(mockRepo);

        Integer id = 1;
        IoTDevice device = new IoTDevice(id, "Device 1", "Temperature Sensor", true, LocalDateTime.now());

        Mockito.when(mockRepo.findById(id)).thenReturn(device);

        ResponseDTO response = service.getDeviceById(id);

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Iot device found", response.getMessage());

        IoTDevice cachedDevice = service.getFromCache(id);
        Assertions.assertNotNull(cachedDevice);
        Assertions.assertEquals("Device 1", cachedDevice.getName());
    }

    @Test
    void testGetAllDevices() {
        // Mock repository
        IoTDeviceInMemoryRepository mockRepo = Mockito.mock(IoTDeviceInMemoryRepository.class);

        // Create service with mock repository
        IoTDeviceImpl service = new IoTDeviceImpl(mockRepo);

        // Test data: create some devices
        Integer id1 = 1;
        IoTDevice device1 = new IoTDevice(id1, "Device 1", "Temperature Sensor", true, LocalDateTime.now());

        Integer id2 = 2;
        IoTDevice device2 = new IoTDevice(id2, "Device 2", "Humidity Sensor", true, LocalDateTime.now());

        List<IoTDevice> deviceList = Arrays.asList(device1, device2);

        Mockito.when(mockRepo.findAll()).thenReturn(deviceList);

        ResponseDTO response = service.getDevices();

        Assertions.assertEquals(200, response.getStatus()); // Status should be 200 OK
        Assertions.assertEquals("Iot devices retrieved successfully", response.getMessage()); // Message should indicate devices were found

        List<IoTDevice> devices = (List<IoTDevice>) response.getData();
        Assertions.assertEquals(2, devices.size()); // Verify there are 2 devices in the list
        Assertions.assertEquals("Device 1", devices.get(0).getName()); // Verify the name of the first device
        Assertions.assertEquals("Device 2", devices.get(1).getName()); // Verify the name of the second device
    }

}
