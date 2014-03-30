package com.gms.gui;

import gnu.io.CommPortIdentifier;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Font;

public class RealTimePanel extends JPanel {
	//final String DEGREE  = "\u00b0";
	final String DEGREE_F  = "\u2109";
	//final String DEGREE_C  = "\u2103";
	
	private JTextField txtLightValue;
	private JTextField txtMoistureValue;
	private JTextField txtTemperatureValue;
	private JTextField txtHumidityValue;
	
	private JLabel lblLightOn;
	private JLabel lblFanOn;
	private JLabel lblPumpOn;
	private JLabel lblHumidifierOn;
	
	private JLabel lblLampIsOn;
	private JLabel lblFanIsOn;
	private JLabel lblPumpIsOn;
	private JLabel lblHumidifierIsOn;
	
	/**
	 * Create the panel.
	 */
	public RealTimePanel() {
		setBackground(new Color(102, 102, 102));
		setLayout(null);
		
		JLabel lblLight = new JLabel("");
		lblLight.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/Sun.png")));
		lblLight.setToolTipText("Light");
		lblLight.setHorizontalAlignment(SwingConstants.CENTER);
		lblLight.setBounds(68, 40, 48, 48);
		add(lblLight);
		
		txtLightValue = new JTextField();
		txtLightValue.setForeground(new Color(204, 0, 0));
		txtLightValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLightValue.setText("xxxxxxxx");
		txtLightValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtLightValue.setEditable(false);
		txtLightValue.setColumns(10);
		txtLightValue.setBounds(135, 40, 86, 48);
		add(txtLightValue);
		
		txtMoistureValue = new JTextField();
		txtMoistureValue.setForeground(new Color(204, 0, 0));
		txtMoistureValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtMoistureValue.setText("xxxxxxxx");
		txtMoistureValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtMoistureValue.setEditable(false);
		txtMoistureValue.setColumns(10);
		txtMoistureValue.setBounds(135, 122, 86, 48);
		add(txtMoistureValue);
		
		JLabel lblMoisture = new JLabel("");
		lblMoisture.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/Rain.png")));
		lblMoisture.setToolTipText("Moisture");
		lblMoisture.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoisture.setBounds(68, 122, 48, 48);
		add(lblMoisture);
		
		JLabel lblTemperature = new JLabel("");
		lblTemperature.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/Thermometer.png")));
		lblTemperature.setToolTipText("Temperature");
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);
		lblTemperature.setBounds(68, 207, 48, 48);
		add(lblTemperature);
		
		txtTemperatureValue = new JTextField();
		txtTemperatureValue.setForeground(new Color(204, 0, 0));
		//txtTemperatureValue.setFont(new Font("Tahoma", Font.BOLD));
		txtTemperatureValue.setFont(txtTemperatureValue.getFont().deriveFont(txtTemperatureValue.getFont().getStyle() | Font.BOLD, 14f));
		txtTemperatureValue.setText("xxxxxxxx");
		txtTemperatureValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtTemperatureValue.setEditable(false);
		txtTemperatureValue.setColumns(10);
		txtTemperatureValue.setBounds(135, 207, 86, 48);
		add(txtTemperatureValue);
		
		JLabel lblHumidity = new JLabel("");
		lblHumidity.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/Humidity.png")));
		lblHumidity.setToolTipText("Temperature");
		lblHumidity.setHorizontalAlignment(SwingConstants.CENTER);
		lblHumidity.setBounds(68, 296, 48, 48);
		add(lblHumidity);
		
		txtHumidityValue = new JTextField();
		txtHumidityValue.setText("xxxxxxxx");
		txtHumidityValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtHumidityValue.setForeground(new Color(204, 0, 0));
		txtHumidityValue.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtHumidityValue.setEditable(false);
		txtHumidityValue.setColumns(10);
		txtHumidityValue.setBounds(135, 296, 86, 48);
		add(txtHumidityValue);
		
		lblLightOn = new JLabel("");
		lblLightOn.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/LightBulb.png")));
		lblLightOn.setToolTipText("Light");
		lblLightOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLightOn.setBounds(258, 40, 48, 48);
		lblLightOn.setVisible(false);
		add(lblLightOn);
		
		lblFanOn = new JLabel("");
		lblFanOn.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/Fan.png")));
		lblFanOn.setToolTipText("Light");
		lblFanOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanOn.setBounds(258, 207, 48, 48);
		lblFanOn.setVisible(false);
		add(lblFanOn);
		
		lblPumpOn = new JLabel("");
		lblPumpOn.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/WateringCan.png")));
		lblPumpOn.setToolTipText("Light");
		lblPumpOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPumpOn.setBounds(258, 122, 48, 48);
		lblPumpOn.setVisible(false);
		add(lblPumpOn);
		
		lblHumidifierOn = new JLabel("");
		lblHumidifierOn.setIcon(new ImageIcon(RealTimePanel.class.getResource("/com/gms/images/WateringCan.png")));
		lblHumidifierOn.setToolTipText("Light");
		lblHumidifierOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHumidifierOn.setBounds(258, 296, 48, 48);
		lblHumidifierOn.setVisible(false);
		add(lblHumidifierOn);
		
		lblLampIsOn = new JLabel("Lamp is On!");
		lblLampIsOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLampIsOn.setForeground(Color.WHITE);
		lblLampIsOn.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblLampIsOn.setBounds(318, 55, 100, 16);
		lblLampIsOn.setVisible(false);
		add(lblLampIsOn);
		
		lblPumpIsOn = new JLabel("Pump is On!");
		lblPumpIsOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblPumpIsOn.setForeground(Color.WHITE);
		lblPumpIsOn.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblPumpIsOn.setBounds(318, 137, 100, 16);
		lblPumpIsOn.setVisible(false);
		add(lblPumpIsOn);
		
		lblFanIsOn = new JLabel("Fan is On!");
		lblFanIsOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblFanIsOn.setForeground(Color.WHITE);
		lblFanIsOn.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblFanIsOn.setBounds(318, 223, 100, 16);
		lblFanIsOn.setVisible(false);
		add(lblFanIsOn);
		
		lblHumidifierIsOn = new JLabel("Humidifier is On!");
		lblHumidifierIsOn.setHorizontalAlignment(SwingConstants.CENTER);
		lblHumidifierIsOn.setForeground(Color.WHITE);
		lblHumidifierIsOn.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblHumidifierIsOn.setBounds(318, 312, 126, 16);
		lblHumidifierIsOn.setVisible(false);
		add(lblHumidifierIsOn);

	}

	public void updateTextValues(String sensorName, Float value){
			
		if(sensorName.equals("Light"))
		{
			txtLightValue.setText(value.toString());
		}
		else if(sensorName.equals("Temperature"))
		{
			txtTemperatureValue.setText(value.toString() + " " + DEGREE_F);
		}
		else if(sensorName.equals("Moisture"))
		{
			txtMoistureValue.setText(value.toString());
		}
		else if(sensorName.equals("Humidity"))
		{
			txtHumidityValue.setText(value.toString() + " %");
		}
		else
		{
			System.err.println("Update Value did not find a match");
			System.err.println("sensor = " + sensorName);
		}
	}
	public void updateHardwareNotifs(String hardware, int state){
		if(hardware.equals("LIGHT")){
			if(state == 1){
				lblLightOn.setVisible(true);
				lblLampIsOn.setVisible(true);
			}
			else{
				lblLightOn.setVisible(false);
				lblLampIsOn.setVisible(false);
			}
		}
		else if(hardware.equals("PUMP")){
			if(state == 1){
				lblPumpOn.setVisible(true);
				lblPumpIsOn.setVisible(true);
			}
			else{
				lblPumpOn.setVisible(false);
				lblPumpIsOn.setVisible(false);	
			}
		}
		else if(hardware.equals("FAN")){
			if(state == 1){
				lblFanOn.setVisible(true);
				lblFanIsOn.setVisible(true);
			}
			else{
				lblFanOn.setVisible(false);
				lblFanIsOn.setVisible(false);			
			}
		}
		else if(hardware.equals("HUMIDIFIER")){
			if(state == 1){
				lblHumidifierOn.setVisible(true);
				lblHumidifierIsOn.setVisible(true);
			}
			else{
				lblHumidifierOn.setVisible(false);
				lblHumidifierIsOn.setVisible(false);
			}
		}
		else
		{
			System.err.println("Update Hardware did not find a match");
			System.err.println("hardware = " + hardware);
		}
	}
}
