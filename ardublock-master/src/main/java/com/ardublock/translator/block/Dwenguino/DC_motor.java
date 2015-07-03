package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
//import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
	
public class DC_motor extends TranslatorBlock
{

	public DC_motor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{	
		TranslatorBlock ChannelBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock SpeedBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String ChannelNumber = ChannelBlock.toCode();
		String Speed = SpeedBlock.toCode();
		String MotorDeclare;
		String MotorName;
		
		if (Integer.parseInt(ChannelNumber) > 2 || Integer.parseInt(ChannelNumber) < 1) 
		{
			throw new BlockException(this.blockId, "the Channel# of DC Motor must be 1 or 2");
			//ChannelNumber = "1 or 2";
		}
		if (Integer.parseInt(Speed) > 255 || Integer.parseInt(Speed) < -255) 
		{
			throw new BlockException(this.blockId, "the Speed of DC Motor must be -255(full speed backward) to 255(full speed forward)");
			//Speed = "-255 to 255";
		}
		
		if (Integer.parseInt(ChannelNumber) == 1)
		{
			MotorDeclare = "DCMotor dcMotor1(MOTOR_1_0, MOTOR_1_1);";
			MotorName = "dcMotor1";
		}
		else
		{
			MotorDeclare = "DCMotor dcMotor2(MOTOR_2_0, MOTOR_2_1);";
			MotorName = "dcMotor2";
		}
		
			
		String ret = "\t" + MotorName + ".setSpeed(" + Speed + ");\n";
		translator.addDefinitionCommand(MotorDeclare);
		translator.addHeaderFile("Dwenguino.h");
        translator.addHeaderFile("DwenguinoMotor.h"); // the motor library
		return ret;
		}

	}
