package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import com.jad.nexaspringhelloworld.repository.result.PersistenceOperationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
    default PersistenceOperationResult update(Integer id, String name) {
        return StoredProcedureResult.map(StoredProcedureResult.fromMessage(this.languageUpdateProc(id, name)));
    }

    @Procedure(procedureName = "helloworld.updateLanguage", outputParameterName = "errorMessage_")
    String languageUpdateProc(Integer id, String name);

    default PersistenceOperationResult create(final String name) {
        return StoredProcedureResult.map(StoredProcedureResult.fromMap(this.languageCreateProc(name)));
    }

    @Procedure(name = "language.create")
    Map<String, ?> languageCreateProc(@Param("_languageName") String languageName);

    default PersistenceOperationResult delete(Integer id) {
        return StoredProcedureResult.map(StoredProcedureResult.fromMessage(this.languageDeleteProc(id)));
    }

    @Procedure(procedureName = "helloworld.deleteLanguage", outputParameterName = "errorMessage_")
    String languageDeleteProc(Integer id);

    default PersistenceOperationResult undelete(Integer id) {
        return StoredProcedureResult.map(StoredProcedureResult.fromMessage(this.languageUndeleteProc(id)));
    }

    @Procedure(procedureName = "helloworld.undeleteLanguage", outputParameterName = "errorMessage_")
    String languageUndeleteProc(Integer id);
}
