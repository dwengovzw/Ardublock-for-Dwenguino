# Ardublock
A Graphical Programming Language for Dwenguino

Prerequisites: Arduino IDE and Maven tool 
	Install Arduino IDE https://www.arduino.cc/en/Main/Software
	Install Maven https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

Included with Specific Dwenguino modules as follows,
	LEDS -  Control 8 bit array LEDs 
	DC Motor - Control 2 DC motor
	LCD - Access LCD in Dwenguino
	Sonar - Access ultrasonic sensor
	Servo - Control 2 Servos 
	Sensor Panel - Access sensor panel board(an extension board with Dwenguino)
	Remote - Access TSOP1738 sensor output for Input from Universal TV remote (Sony protocol - tested with MEPL SONY TV REMOTE)

Check here on how to use the blocks - http://www.dwengo.org/ArduBlock

Steps to generate and use ardublock-all.jar for Dwenguino

	1. Download Zip of this repository (which will download as Ardublock-for-Dwenguino-master.zip)
	2. Unzip the repository
	3. Using command line navigate to Ardublock-for-Dwenguino-master\openblocks-master and type command "mvn clean install"
	4. Then navigate to Ardublock-for-Dwenguino-master\ardublock-master and type command "mvn clean install"
	5. Then go to Ardublock-for-Dwenguino-master\ardublock-master\target\ and copy ardublock-all.jar
	6. Then create folder Documents\Arduino\tools\ArduBlockTool\tool\ and paste ardublock-all.jar here.
	7. Open Arduino IDE, goto Tools --> ArduBlock

That's it..!!
