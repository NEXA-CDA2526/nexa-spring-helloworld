package com.jad.nexaspringhelloworld.command.language;

import com.jad.nexaspringhelloworld.dto.LanguageId;

public record LanguageDeleteCommand(LanguageId id) implements LanguageCommand {
}
