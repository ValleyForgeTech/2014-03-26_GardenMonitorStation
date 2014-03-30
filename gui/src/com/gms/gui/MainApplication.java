package com.gms.gui;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.gms.serial.ISerialListener;
import com.gms.serial.SerialUtility;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.util.HashMap;

import javax.swing.JComboBox;

public class MainApplication implements ISerialListener{

	private JFrame frmGardenmonitorstation;
	private static RealTimePanel _panel_realtime;
	private static JComboBox<String> cboxPorts;
	
	public static BufferedReader input;
	public static OutputStream output;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApplication window = new MainApplication();
					window.frmGardenmonitorstation.setVisible(true);
					//Initialize Service
					window.InitializeSerialCommunication();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGardenmonitorstation = new JFrame();
		frmGardenmonitorstation.setIconImage(Toolkit.getDefaultToolkit().getImage(MainApplication.class.getResource("/com/gms/images/Leaf.png")));
		frmGardenmonitorstation.setTitle("GardenMonitorStation");
		frmGardenmonitorstation.setResizable(false);
		frmGardenmonitorstation.getContentPane().setBackground(Color.DARK_GRAY);
		frmGardenmonitorstation.getContentPane().setLayout(null);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MainApplication.class.getResource("/com/gms/images/Configure.png")));
		button.setSelectedIcon(new ImageIcon(MainApplication.class.getResource("/com/gms/images/Configure.png")));
		button.setToolTipText("Configure...");
		button.setBounds(412, 6, 48, 48);
		frmGardenmonitorstation.getContentPane().add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 44, 450, 404);
		frmGardenmonitorstation.getContentPane().add(tabbedPane);
		
		_panel_realtime = new RealTimePanel();
		tabbedPane.addTab("RealTime", null, _panel_realtime, null);
		_panel_realtime.setLayout(null);
		
		JPanel panel_history = new JPanel();
		panel_history.setBackground(Color.GRAY);
		tabbedPane.addTab("History", null, panel_history, null);
		
		JPanel panel_analysis = new JPanel();
		panel_analysis.setBackground(Color.GRAY);
		tabbedPane.addTab("Analysis", null, panel_analysis, null);
		
		cboxPorts = new JComboBox<String>();
		cboxPorts.setBounds(10, 11, 236, 20);
		frmGardenmonitorstation.getContentPane().add(cboxPorts);
		frmGardenmonitorstation.setBounds(100, 100, 475, 488);
		frmGardenmonitorstation.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void InitializeSerialCommunication(){
		try{
		SerialUtility obj = new SerialUtility();
		obj.initialize(this);
		
		System.out.println("Garden Monitoring Station");
		//update combobox
		cboxPorts.setModel(new DefaultComboBoxModel<String>(obj.getCommPorts()));
		
		System.out.println("Initialized. Waiting for input...");
		//obj.close();
		
		
		}
		catch (Exception e) {
			System.err.println("Exception caught: " + e.toString());
		}
		
	}
	
	public void updateSensorData(String sensorName, Float value)
	{
		_panel_realtime.updateTextValues(sensorName, value);
	}
	
	public void updateHardwareState(String hardwareName, int state)
	{
		_panel_realtime.updateHardwareNotifs(hardwareName, state);
	}
}
