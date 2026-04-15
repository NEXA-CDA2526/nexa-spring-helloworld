package com.jad.nexaspringhelloworld.repository.result;

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
}
