package com.udacity.catpoint.service.security;

import com.udacity.catpoint.application.StatusListener;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.udacity.catpoint.service.image.ImageService;
import com.udacity.catpoint.data.*;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceTest {

    private final String random = UUID.randomUUID().toString();
    private Sensor sensor;
    private SecurityService securityService;

    @Mock
    private ImageService imageService;

    @Mock
    private SecurityRepository securityRepository;

    @Mock
    private StatusListener statusListener;

    @BeforeEach
    void init() {
        securityService = new SecurityService(securityRepository, imageService);
        sensor = new Sensor(random, SensorType.DOOR);
    }

    // 1. If alarm is armed and a sensor becomes activated, put the system into pending alarm status.
    @Test
    void whenAlarmIsArmedAndSensorIsActivated_alarmStatusShouldBePending() {
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.ARMED_HOME);
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.NO_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.PENDING_ALARM);
    }

    // 2. If alarm is armed and a sensor becomes activated and the system is already pending alarm, set the alarm
    // status to alarm.
    @Test
    void whenAlarmIsArmedAndSensorIsActivatedAndSystemStatusInPending_AlarmShouldBeSetOff() {
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.ARMED_HOME);
        when(securityService.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.ALARM);
    }

    // 3. If pending alarm and all sensors are inactive, return to no alarm state.
    @Test
    void whenAlarmIsPendingAndSensorsIsInactive_statusShouldBeNoAlarm() {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        sensor.setActive(false);
        securityService.changeSensorActivationStatus(sensor, null);
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // 4. If alarm is active, change in sensor state should not affect the alarm state.
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void whenAlarmIsActivatedAndSensorStateChanged_alarmStateShouldNotChange(boolean status) {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.ALARM);
        securityService.changeSensorActivationStatus(sensor, status);
        verify(securityRepository, never()).setAlarmStatus(any(AlarmStatus.class));
    }

    // 5. If a sensor is activated while already active and the system is in pending state, change it to alarm state.
    @Test
    void whenSensorActivatedWhileActiveAndSystemIsInPending_stateShouldBeAlarm() {
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        sensor.setActive(true);
        securityService.changeSensorActivationStatus(sensor, true);
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.ALARM);
    }

    // 6. If a sensor is deactivated while already inactive, make no changes to the alarm state.
    @ParameterizedTest
    @EnumSource(value = AlarmStatus.class, names = {"ALARM", "PENDING_ALARM", "NO_ALARM"})
    void whenSensorIsDeactivatedWhileInactive_stateShouldBeNoChange(AlarmStatus status) {
        when(securityRepository.getAlarmStatus()).thenReturn(status);
        sensor.setActive(false);
        securityService.changeSensorActivationStatus(sensor, false);
        verify(securityRepository, never()).setAlarmStatus(any(AlarmStatus.class));
    }

    // 7. If the image service identifies an image containing a cat while the system is armed-home, put the
    // system into alarm status.
    @Test
    void whenCatDetectedWhileAlarmed_statusShouldBeAlarm() {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.ARMED_HOME);
        when(imageService.imageContainsCat(any(), ArgumentMatchers.anyFloat())).thenReturn(true);
        securityService.processImage(image);
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.ALARM);
    }

    private Set<Sensor> generateSensors(int count, boolean status) {
        Set<Sensor> sensors = new HashSet<>();
        int i = 0;
        while (i < count) {
            Sensor sensor = new Sensor(random, SensorType.DOOR);
            sensor.setActive(status);
            sensors.add(sensor);
            i++;
        }
        return sensors;
    }

    // 8. If the image service identifies an image that does not contain a cat, change the status to no alarm as
    // long as the sensors are not active.
    @Test
    void whenNoCatDetectedAsLongAsSensorsNotActive_statusShouldBeNoAlarm() {
        Set<Sensor> sensors = generateSensors(3, false);
        when(securityRepository.getSensors()).thenReturn(sensors);
        when(imageService.imageContainsCat(any(), ArgumentMatchers.anyFloat())).thenReturn(false);
        securityService.processImage(mock(BufferedImage.class));
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // 9. If the system is disarmed, set the status to no alarm.
    @Test
    void whenSystemDisarmed_statusShouldBeNoAlarm() {
        securityService.setArmingStatus((ArmingStatus.DISARMED));
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.NO_ALARM);
    }

    // 10. If the system is armed, reset all sensors to inactive.
    @ParameterizedTest
    @EnumSource(value = ArmingStatus.class, names = {"ARMED_HOME", "ARMED_AWAY"})
    void whenSystemArmed_sensorsShouldBeInactive(ArmingStatus armingStatus) {
        Set<Sensor> sensors = generateSensors(3, true);
        when(securityRepository.getAlarmStatus()).thenReturn(AlarmStatus.PENDING_ALARM);
        when(securityRepository.getSensors()).thenReturn(sensors);
        securityService.setArmingStatus(armingStatus);
        securityService.getSensors().forEach(sensor -> assertFalse(sensor.getActive()));
    }

    // 11. If the system is armed-home while the camera shows a cat, set the alarm status to alarm.
    @Test
    void whenArmedHomeAndCatDetected_statusShouldBeAlarm() {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);
        when(imageService.imageContainsCat(any(), anyFloat())).thenReturn(true);
        when(securityRepository.getArmingStatus()).thenReturn(ArmingStatus.DISARMED);
        securityService.processImage(image);
        securityService.setArmingStatus(ArmingStatus.ARMED_HOME);
        verify(securityRepository, atLeastOnce()).setAlarmStatus(AlarmStatus.ALARM);
    }

    /* Extra unit tests for complete class coverage */

    @Test
    void addStatusListener() {
        securityService.addStatusListener(statusListener);
    }

    @Test
    void removeStatusListener() {
        securityService.removeStatusListener(statusListener);
    }

    @Test
    public void addRemoveStatusListener() {
        securityService.addStatusListener(statusListener);
        securityService.removeStatusListener(statusListener);
    }

    @Test
    void addSensor() {
        securityService.addSensor(sensor);
    }

    @Test
    void removeSensor() {
        securityService.removeSensor(sensor);
    }

    @ParameterizedTest
    @EnumSource(ArmingStatus.class)
    public void setArmingStatus(ArmingStatus status) {
        securityService.setArmingStatus(status);
    }

}