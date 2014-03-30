package com.gms.serial;


public interface ISerialListener {
	public void updateSensorData(String sensorName, Float value);
	public void updateHardwareState(String hardwareName, int state);
}
