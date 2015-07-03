package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Ultrasonic extends TranslatorBlock
{
	public Ultrasonic(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String trigPin;
		String echoPin;
		TranslatorBlock translatorBlock;
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		trigPin = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		echoPin = translatorBlock.toCode();
		translator.addDefinitionCommand("#define ECHO_PIN_"+trigPin+echoPin+"sensor"+" "+echoPin+"\n#define TRIGGER_PIN_"+trigPin+echoPin+"sensor"+" "+trigPin+"\n#define MAX_DISTANCE_"+trigPin+echoPin+"sensor"+" "+"200"+"\nNewPing "+"UV"+trigPin+echoPin+"sensor"+"(TRIGGER_PIN_"+trigPin+echoPin+"sensor"+",ECHO_PIN_"+trigPin+echoPin+"sensor"+",MAX_DISTANCE_"+trigPin+echoPin+"sensor"+");");
		translator.addHeaderFile("NewPing.h");
		String ret = "\t"+"UV"+trigPin+echoPin+"sensor"+".ping() / US_ROUNDTRIP_CM";

		return ret;
	}
	
}
