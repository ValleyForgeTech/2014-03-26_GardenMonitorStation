package com.gms.serial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;


//import SerialClass;

public class TestClass {
	public static BufferedReader input;
	public static OutputStream output;

	public static synchronized void writeData(String data) {
		System.out.println("Sent: " + data);
		try {
			output.write(data.getBytes());
		} catch (Exception e) {
			System.out.println("could not write to port");
		}
	}

	public static void main(String[] ag) {

		try {
			SerialUtility obj = new SerialUtility();
			//obj.initialize();
			input = SerialUtility.input;
			output = SerialUtility.output;
			//InputStreamReader Ir = new InputStreamReader(System.in);
			//BufferedReader Br = new BufferedReader(Ir);

			System.out.println("Garden Monitoring Station");
			System.out.println("Initialized. Waiting for input...");
			String inputLine = input.readLine();
			System.out.println(inputLine);

			obj.close();

		} catch (Exception e) {
		}

	}
}