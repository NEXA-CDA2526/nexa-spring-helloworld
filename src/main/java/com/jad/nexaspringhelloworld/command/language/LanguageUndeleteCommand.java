package com.jad.nexaspringhelloworld.command.language;

import com.jad.nexaspringhelloworld.dto.LanguageId;

public record LanguageUndeleteCommand(LanguageId id) implements LanguageCommand {
}
