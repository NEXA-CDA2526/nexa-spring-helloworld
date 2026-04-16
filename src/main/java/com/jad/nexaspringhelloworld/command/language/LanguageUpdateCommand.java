package com.jad.nexaspringhelloworld.command.language;

import com.jad.nexaspringhelloworld.dto.LanguageData;
import com.jad.nexaspringhelloworld.dto.LanguageId;

public record LanguageUpdateCommand(LanguageId id, LanguageData data) implements LanguageCommand {
}
