package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.repository.result.PersistenceOperationResult;

import java.util.Map;


sealed interface StoredProcedureResult
        permits StoredProcedureResult.SimpleStoredProcedureResult, StoredProcedureResult.StoredProcedureResultWithId {
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

    static PersistenceOperationResult map(StoredProcedureResult storedProcedureResult) {
        if (storedProcedureResult.success()) {
            switch (storedProcedureResult) {
                case StoredProcedureResultWithId storedProcedureResultWithId -> {
                    Integer id = StoredProcedureResultWithId.getId(storedProcedureResultWithId);
                    return PersistenceOperationResult.ok(id);
                }
                case SimpleStoredProcedureResult ignored -> PersistenceOperationResult.ok();
            }
        }
        return PersistenceOperationResult.fail(storedProcedureResult.message());
    }

    boolean success();

    String message();

    record SimpleStoredProcedureResult(boolean success, String message) implements StoredProcedureResult {
    }

    record StoredProcedureResultWithId(boolean success,
                                       String message,
                                       Integer id) implements StoredProcedureResult {
        public static Integer getId(final StoredProcedureResultWithId storedProcedureResultWithId) {
            return storedProcedureResultWithId.id();
        }
    }
}
