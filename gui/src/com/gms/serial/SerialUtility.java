package com.gms.serial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

public class SerialUtility implements SerialPortEventListener {

	public ISerialListener _listener;
	public SerialPort serialPort;
	public HashMap<String, CommPortIdentifier> _commPorts = new HashMap<String, CommPortIdentifier>();
	
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = {"COM3"};
	//private static final String PORT_NAMES[] = {"/dev/tty.usbmodemfd131"};

	public static BufferedReader input;
	public static OutputStream output;
	/** Milliseconds to block while waiting for port open */
	public static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	public static final int DATA_RATE = 9600;

	private void mapCommPorts(){
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			System.out.println("currPortId = " + currPortId.getName());
			//ADD TO MAP
			_commPorts.put(currPortId.getName(), currPortId);
		}
	}
	
	public String[] getCommPorts(){
		return _commPorts.keySet().toArray( new String[0] );
	}
	
	public void initialize(ISerialListener inListener) {
		_listener = inListener;
		
		mapCommPorts();
		
		CommPortIdentifier portId = null;
		if(_commPorts.containsKey(PORT_NAMES[0]))
		{
			portId = _commPorts.get(PORT_NAMES[0]);
			System.out.println("found port: " + PORT_NAMES[0]);
		}
		if (portId == null) {
			System.out.println("Could not find selected COM port: " + PORT_NAMES[0]);
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(
					serialPort.getInputStream()));
			output = serialPort.getOutputStream();
			char ch = 1;
			output.write(ch);

			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				if(input.ready())
				{
				String inputLine = input.readLine();
				System.out.println(inputLine);
				//CALL UPDATE METHODS - SENSOR:{VALUE}
				String[] dataString = inputLine.split(":");
				String message = dataString[0];
				
				if(message.equals("SENSOR"))
				{
					String sensor = dataString[1];
					Float value = Float.parseFloat(dataString[2]);
					_listener.updateSensorData(sensor, value);
				}
				else if(message.equals("ACTION"))
				{
					String hardwareName = dataString[1];
					int state = Integer.parseInt(dataString[2]);
					_listener.updateHardwareState(hardwareName, state);
				}
				else
				{
					System.err.println("Unkown data message: " + message);
				}
				}
				
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		else{
			System.err.println("Unkown data event");
		}

	}

	public static synchronized void writeData(String data) {
		System.out.println("Sent: " + data);
		try {
			output.write(data.getBytes());
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}

}