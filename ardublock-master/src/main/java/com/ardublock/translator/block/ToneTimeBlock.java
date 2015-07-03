package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class ToneTimeBlock extends TranslatorBlock
{
	public ToneTimeBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws SocketNullException , SubroutineNotDeclaredException
	{
		TranslatorBlock freqBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock timeBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String ret = "tone(BUZZER, " + freqBlock.toCode() + ", " + timeBlock.toCode() + ");\n";
		return ret;
	}
}
