#include <dht11.h>
dht11 DHT11;
#define DHT11PIN 2

// Loop Delay = How often to take reading (ms) : 15min = 900000
int loopDelay = 5000;
int writeDelay = 25;

// declare sensor pins
int sensorPin_light = A0;
int sensorPin_moisture = A1;
int sensorPin_temp = 2;

// declare sensor values
int sensorVal_light = 0;
int sensorVal_moisture = 0;
int sensorVal_temp = 0;
int sensorVal_humidity = 0;

//thresholds
int threshold_light = 1000;
int threshold_moisture = 600;
int threshold_temp = 60; //degrees F
int humidity_threshold = 40; //percent

int trigger_Light = 10;
int trigger_Temp = 11;
int trigger_Moisture = 12;
int trigger_Humidity = 13;

//Celsius to Fahrenheit conversion
double Fahrenheit(double celsius)
{
    return 1.8 * celsius + 32;
}


void setup() {
  //initialize serial ports
  Serial.begin(9600);

  pinMode(trigger_Light, OUTPUT); 
  pinMode(trigger_Temp, OUTPUT); 
  pinMode(trigger_Humidity, OUTPUT);
  pinMode(trigger_Moisture, OUTPUT); 

}

void loop() {
  // read the values from the sensors:
  readSensors();
  
  //transmit data to PC
  transmitData();
  delay(1000);  
  
  //evaluate temp data for further action
  evaluateTemperatureData();
  delay(1000); 
  
  //evaluate temp data for further action
  //evaluateHumidityData();
  //delay(1000); 
  
  //evaluate moisture data for further action
  evaluateMoistureData();
  delay(1000); 
  
  //evaluate light data for further action
  //evaluateLightData();
  //delay(1000); 

  //wait to take next reading
  delay(loopDelay);
}

void readSensors() {
  // read the values from the sensors
  sensorVal_light = analogRead(sensorPin_light);
  delay(100);
  
  sensorVal_moisture = analogRead(sensorPin_moisture);
  delay(100);
  
  readTemperatureandHumiditySesnsor();
}

void readTemperatureandHumiditySesnsor()
{
  int chk = DHT11.read(DHT11PIN);
  sensorVal_humidity = (float)DHT11.humidity;
  sensorVal_temp = Fahrenheit(DHT11.temperature);
}

void transmitData() {
  // Transmit the data over the XBee to PC
  Serial.print("SENSOR:Light:");
  Serial.println(sensorVal_light);
  delay(writeDelay);
  
  Serial.print("SENSOR:Moisture:");
  Serial.println(sensorVal_moisture);
  delay(writeDelay);
  
  Serial.print("SENSOR:Temperature:");
  Serial.println((double)sensorVal_temp, 2);
  delay(writeDelay);
  
  Serial.print("SENSOR:Humidity:");
  Serial.println((float)sensorVal_humidity, 2);
  delay(writeDelay);
}
  


void evaluateTemperatureData(){
  //if temperature is too high, turn on a fan
  if(sensorVal_temp > threshold_temp){
    //Send message to PC that Fan is on
    Serial.println("ACTION:FAN:1");
    delay(writeDelay);
    
    //turn on fan for X mins
    digitalWrite(trigger_Temp, HIGH); 
    delay(5000);
    digitalWrite(trigger_Temp, LOW); 
    
    //Send message to PC that Fan is off
    Serial.println("ACTION:FAN:0");
    delay(writeDelay);
  }
}

void evaluateMoistureData(){
  //if soil moisture is too low, turn on a pump for watering
  if(sensorVal_moisture < threshold_moisture)
  {
    //Send message to PC that pump is on
    Serial.println("ACTION:PUMP:1");
    delay(writeDelay);
    
    //turn on water pump for X mins
    digitalWrite(trigger_Moisture, HIGH); 
    delay(5000);
    digitalWrite(trigger_Moisture, LOW); 
    
    //Send message to PC that pump is off
    Serial.println("ACTION:PUMP:0");
    delay(writeDelay);
  }

}


