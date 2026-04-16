package com.jad.nexaspringhelloworld.command;

import com.jad.nexaspringhelloworld.dto.LanguageOutput;

public record CommandResult<E>(E payload) {
    public static <E> CommandResult<E> withPayLoad(final E payLoad) {
        return new CommandResult<>(payLoad);
    }

    public static <E> CommandResult<E> noPayLoad() {
        return new CommandResult<>(null);
    }

    public static LanguageOutput getPayLoadAndThrowIfNull(final CommandResult<LanguageOutput> commandResult,
                                                          final String message) {
        return commandResult.requirePayLoad(message);
    }

    public E requirePayLoad(final String message) {
        if (this.payload == null) throw new IllegalStateException(message);
        return this.payload;
    }
}
