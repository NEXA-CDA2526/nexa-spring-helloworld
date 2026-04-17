package com.jad.nexaspringhelloworld.repository.result;

import java.util.Optional;

public record PersistenceOperationResult(boolean success,
                                         String message,
                                         Optional<Integer> id) {
    public static PersistenceOperationResult fail(final String message) {
        return new PersistenceOperationResult(false, message, Optional.empty());
    }

    public static PersistenceOperationResult ok(final Integer id) {
        return new PersistenceOperationResult(true, null, Optional.of(id));
    }

    public static PersistenceOperationResult ok() {
        return new PersistenceOperationResult(true, null, Optional.empty());
    }

    public Integer realId() {
        return id.orElseThrow(() -> new IllegalStateException("No ID present in a successful operation result"));
    }
}
