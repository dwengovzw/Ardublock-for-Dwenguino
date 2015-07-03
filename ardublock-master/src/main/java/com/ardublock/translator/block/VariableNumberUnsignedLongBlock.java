package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableNumberUnsignedLongBlock extends TranslatorBlock
{
  public VariableNumberUnsignedLongBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
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
      translator.addDefinitionCommand("unsigned long " + internalVariableName + " = 0UL ;");
//      translator.addSetupCommand(internalVariableName + " = 0;");
    }
    return codePrefix + internalVariableName + codeSuffix;
  }

}