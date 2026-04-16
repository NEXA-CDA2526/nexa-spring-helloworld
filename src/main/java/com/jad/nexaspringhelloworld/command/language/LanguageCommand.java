package com.jad.nexaspringhelloworld.command.language;

import com.jad.nexaspringhelloworld.dto.LanguageData;
import com.jad.nexaspringhelloworld.dto.LanguageId;

public sealed interface LanguageCommand
        permits LanguageCreateCommand, LanguageUpdateCommand, LanguageDeleteCommand, LanguageUndeleteCommand {

    static String getName(final LanguageCommand languageCommand) {
        return switch (languageCommand) {
            case LanguageCreateCommand command -> LanguageData.getName(command.data());
            case LanguageUpdateCommand command -> LanguageData.getName(command.data());
            default -> throw new IllegalArgumentException("No name in this command");
        };
    }

    static Integer getId(final LanguageCommand languageCommand) {
        return switch (languageCommand) {
            case LanguageCreateCommand command -> throw new IllegalArgumentException("No id in this command");
            case LanguageUpdateCommand command -> LanguageId.getId(command.id());
            case LanguageDeleteCommand command -> LanguageId.getId(command.id());
            case LanguageUndeleteCommand command -> LanguageId.getId(command.id());
        };
    }
}
