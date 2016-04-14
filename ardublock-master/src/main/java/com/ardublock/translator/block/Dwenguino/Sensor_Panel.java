package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Sensor_Panel extends TranslatorBlock{
	
	public Sensor_Panel(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public String toCode() throws SocketNullException,
			SubroutineNotDeclaredException, BlockException {
		TranslatorBlock senseBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock modeBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String sense = senseBlock.toCode();
		String mode = modeBlock.toCode();
		String ret = "\tsensorpanel.readSensor(" + sense + ","+ mode +")";
		translator.addHeaderFile("DwenguinoSensorPanel.h");
		translator.addDefinitionCommand("SensorPanel sensorpanel;");
		translator.addSetupCommand("sensorpanel = SensorPanel();\nsensorpanel.init();\nsensorpanel.powerLongRange(true);");
		return ret;
	}

}
