package com.jad.nexaspringhelloworld.command;

public record CommandResult<E>(E payload) {
    public static <E> CommandResult<E> withPayLoad(final E payLoad) {
        return new CommandResult<>(payLoad);
    }

    public static <E> CommandResult<E> noPayLoad() {
        return new CommandResult<>(null);
    }
}
