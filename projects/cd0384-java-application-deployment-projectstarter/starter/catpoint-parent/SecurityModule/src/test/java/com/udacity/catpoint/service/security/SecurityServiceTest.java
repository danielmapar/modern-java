package com.udacity.catpoint.service.security;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.udacity.catpoint.service.image.ImageService;
import com.udacity.catpoint.data.*;
import com.udacity.catpoint.application.StatusListener;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {
    private SecurityService securityService;
    private Sensor sensor;
    private final String random = UUID.randomUUID().toString();

    @Mock
    private StatusListener statusListener;
    @Mock
    private ImageService imageService;
    @Mock
    private SecurityRepository securityRepository;

    private Sensor makeNewSensor() {
        return new Sensor(random, SensorType.DOOR);
    }

    @BeforeEach
    void init() {
        securityService = new SecurityService(securityRepository, imageService);
        sensor = makeNewSensor();
    }

    // 1. If alarm is armed and a sensor becomes activated, put the system into pending alarm status.
    @Test
    void systemArmedSensorActivated_toPending() {
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.ARMED_HOME);
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.NO_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository, times(1)).setAlarmStatus(AlarmStatus.PENDING_ALARM);
    }
}