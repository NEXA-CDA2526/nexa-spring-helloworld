package com.jad.nexaspringhelloworld.dto;

import com.jad.nexaspringhelloworld.valueobject.Id;

public record LanguageId(Id id) {
    public LanguageId(final Integer id) {
        this(new Id(id));
    }

    public static Integer getId(final LanguageId languageId) {
        return languageId.id().value();
    }
}
