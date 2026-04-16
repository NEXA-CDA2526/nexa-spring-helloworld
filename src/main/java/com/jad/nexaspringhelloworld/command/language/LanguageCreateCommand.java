package com.jad.nexaspringhelloworld.command.language;

import com.jad.nexaspringhelloworld.dto.LanguageData;

public record LanguageCreateCommand(LanguageData data) implements LanguageCommand {
}
