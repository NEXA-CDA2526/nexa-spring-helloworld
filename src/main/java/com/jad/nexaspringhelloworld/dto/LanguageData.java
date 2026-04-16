package com.jad.nexaspringhelloworld.dto;

import com.jad.nexaspringhelloworld.valueobject.Label;

public record LanguageData(Label name) {
    public LanguageData(final String name) {
        this(new Label(name));
    }

    public static String getName(final LanguageData languageData) {
        return languageData.name().value();
    }
}
