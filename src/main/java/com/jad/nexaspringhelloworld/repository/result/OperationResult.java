package com.jad.nexaspringhelloworld.repository.result;

import java.util.function.Function;

public record OperationResult(boolean success, String message) {
    public static OperationResult fromMessage(final String message) {
        if ((message == null) || (message.isBlank())) return OperationResult.ok();
        return OperationResult.fail(message);
    }

    private static OperationResult ok() {
        return new OperationResult(true, null);
    }

    private static OperationResult fail(final String message) {
        return new OperationResult(false, message);
    }

    public static void throwIfFailed(final OperationResult operationResult,
                                     final Function<String, ? extends RuntimeException> exceptionFactory) {
        if (!operationResult.success) throw exceptionFactory.apply(operationResult.message());
    }
}
