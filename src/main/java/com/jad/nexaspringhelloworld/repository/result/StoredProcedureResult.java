package com.jad.nexaspringhelloworld.repository.result;

import java.util.function.Function;

public record StoredProcedureResult(boolean success, String message) {
    public static StoredProcedureResult fromMessage(final String message) {
        if ((message == null) || (message.isBlank())) return StoredProcedureResult.ok();
        return StoredProcedureResult.fail(message);
    }

    private static StoredProcedureResult ok() {
        return new StoredProcedureResult(true, null);
    }

    private static StoredProcedureResult fail(final String message) {
        return new StoredProcedureResult(false, message);
    }

    public static void throwIfFailed(final StoredProcedureResult storedProcedureResult,
                                     final Function<String, ? extends RuntimeException> exceptionFactory) {
        if (!storedProcedureResult.success) throw exceptionFactory.apply(storedProcedureResult.message());
    }
}
