package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class Initialize extends TranslatorBlock
{
	public Initialize(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException,
			SubroutineNotDeclaredException, BlockException {
		translator.addHeaderFile("Wire.h");
		translator.addHeaderFile("Dwenguino.h");
		translator.addHeaderFile("LiquidCrystal.h");
		return "initDwenguino();";
	}
}
