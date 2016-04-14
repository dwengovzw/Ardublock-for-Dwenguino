package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Sensor_Panel_LED extends TranslatorBlock{
	
	public Sensor_Panel_LED(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public String toCode() throws SocketNullException,
			SubroutineNotDeclaredException, BlockException {
		TranslatorBlock ledBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock logicBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String led = ledBlock.toCode();
		String logic = logicBlock.toCode();
		String ret = "\tsensorpanel_LED.setHeadlights(" + led + ","+ logic +");\n";
		translator.addHeaderFile("DwenguinoSensorPanel.h");
		translator.addDefinitionCommand("SensorPanel sensorpanel_LED;");
		translator.addSetupCommand("sensorpanel_LED = SensorPanel();\nsensorpanel_LED.init();");
		return ret;
	}

}
