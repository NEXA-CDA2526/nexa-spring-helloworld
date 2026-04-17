package com.jad.nexaspringhelloworld.repository.result;

public record StoredProcedureResultWithId(boolean success,
                                          String message,
                                          Integer id) implements StoredProcedureResult {

    public static Integer getId(final StoredProcedureResultWithId storedProcedureResultWithId) {
        return storedProcedureResultWithId.id();
    }
}
