package com.jad.nexaspringhelloworld.repository.result;

import java.util.Map;
import java.util.function.Function;

public sealed interface StoredProcedureResult
        permits SimpleStoredProcedureResult, StoredProcedureResultWithId {
    static SimpleStoredProcedureResult fromMessage(String message) {
        if ((message == null) || (message.isBlank())) return StoredProcedureResult.ok();
        return StoredProcedureResult.fail(message);
    }

    static SimpleStoredProcedureResult ok() {
        return new SimpleStoredProcedureResult(true, null);
    }

    static SimpleStoredProcedureResult fail(String message) {
        return new SimpleStoredProcedureResult(false, message);
    }

    static StoredProcedureResultWithId fromMap(Map<String, ?> stringMap) {
        String message = (String) stringMap.get("errorMessage_");
        if ((message == null) || (message.isBlank())) {
            return new StoredProcedureResultWithId(true, null, (Integer) stringMap.get("id_"));
        }
        return new StoredProcedureResultWithId(false, message, null);
    }

    static void throwIfFailed(StoredProcedureResult storedProcedureResult,
                              Function<String, ? extends RuntimeException> exceptionFactory) {
        if (!storedProcedureResult.success()) throw exceptionFactory.apply(storedProcedureResult.message());
    }

    boolean success();

    String message();

}
