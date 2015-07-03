package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LEDS_Value extends TranslatorBlock {
	public LEDS_Value(Long blockId, Translator translator, String codePrefix,
			String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException,
			SubroutineNotDeclaredException, BlockException {
		TranslatorBlock dataBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String data = dataBlock.toCode();
		translator.addHeaderFile("Dwenguino.h");
		if ( !(data.matches("0b[01]{8}")||data.matches("^[a-zA-Z_$][a-zA-Z_$0-9]*$")||data.matches("^[-+]?\\d+$")))
		{
			throw new BlockException(this.blockId, "Must be a valid binary number with 8 bit eg, 0b10011001 or Integer Variable or Integer number");
		}
		return "LEDS="+data.replace("\"", "")+";";
	}
}
