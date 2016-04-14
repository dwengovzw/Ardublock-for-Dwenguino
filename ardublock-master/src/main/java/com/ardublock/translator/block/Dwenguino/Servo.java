
package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Servo extends TranslatorBlock {

	public Servo(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		
		String ret = "";

		int channelNumber = Integer.parseInt(tb.toCode());
		
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		String angle = tb.toCode();

		if ( channelNumber < 0 || channelNumber > 2)
		{
			throw new BlockException(this.blockId, "Servo Channel should be 1 or 2");
		}
		
		if(Integer.parseInt(angle) > 180 || Integer.parseInt(angle) < 0) {
			throw new BlockException(this.blockId, "the angle of Servo must be from 0 to 180");
		}
		
		if(channelNumber == 1) {
			translator.addDefinitionCommand("Servo servo1;");
			translator.addSetupCommand("servo1.attach(SERVO_1);");
			ret = "servo1.write(" + angle + ");\n";
		}
		else {
			translator.addDefinitionCommand("Servo servo2;");
			translator.addSetupCommand("servo2.attach(SERVO_2);");
			ret = "servo2.write(" + angle + ");\n";
		}
		translator.addHeaderFile("Servo.h"); // the motor library
		return ret;
	}

}
