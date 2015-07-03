package com.ardublock.translator.block.Dwenguino;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class LCD extends TranslatorBlock {
	
	public LCD(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(1);
		String lineNo = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		String charNo = tb.toCode();
		if(Integer.parseInt(lineNo) > 1 || Integer.parseInt(lineNo) < 0) {
			throw new BlockException(this.blockId, "the line# of LCD must be 0 or 1");
		}
		if(Integer.parseInt(charNo) > 15 || Integer.parseInt(charNo) < 0) {
			throw new BlockException(this.blockId, "the char# of LCD must be from 0 to 15");
		}
		StringBuilder ret = new StringBuilder();
		tb = this.getRequiredTranslatorBlockAtSocket(0, "dwenguinoLCD.print(", " );");
		ret.append("dwenguinoLCD.setCursor("+charNo+","+lineNo+");\n");
		ret.append(tb.toCode()+"\n");
		translator.addHeaderFile("LiquidCrystal.h");
		translator.addHeaderFile("Wire.h");
		return ret.toString();
	}	
}