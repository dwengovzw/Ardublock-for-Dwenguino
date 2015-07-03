package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableNumberBlock extends TranslatorBlock
{
	public VariableNumberBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode()
	{
		String internalVariableName = translator.getNumberVariable(label);
		if (internalVariableName == null)
		{
			internalVariableName = label.replaceAll("\\s+", "");
			translator.addNumberVariable(internalVariableName, internalVariableName);
			if(!(internalVariableName.matches("0b[01]{8}")||internalVariableName.equals("LEDS")||internalVariableName.matches("^[-+]?\\d+$")))
				translator.addDefinitionCommand("int " + internalVariableName + " = 0 ;");
		//	translator.addSetupCommand(internalVariableName + " = 0;");
		}
		return codePrefix + internalVariableName + codeSuffix;
	}
}
