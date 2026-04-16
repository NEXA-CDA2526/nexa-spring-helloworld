package com.jad.nexaspringhelloworld.repository.result;

public record SimpleStoredProcedureResult(boolean success,
                                          String message) implements StoredProcedureResult {

}
