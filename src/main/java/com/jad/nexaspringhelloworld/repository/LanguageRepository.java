package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import com.jad.nexaspringhelloworld.repository.result.SimpleStoredProcedureResult;
import com.jad.nexaspringhelloworld.repository.result.StoredProcedureResult;
import com.jad.nexaspringhelloworld.repository.result.StoredProcedureResultWithId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.Map;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
    default SimpleStoredProcedureResult update(Integer id, String name) {
        return StoredProcedureResult.fromMessage(this.languageUpdateProc(id, name));
    }

    @Procedure(procedureName = "helloworld.updateLanguage", outputParameterName = "errorMessage_")
    String languageUpdateProc(Integer id, String name);

    default StoredProcedureResultWithId create(final String name) {
        return StoredProcedureResult.fromMap(this.languageCreateProc(name));
    }

    @Procedure(procedureName = "helloworld.createLanguage")
    Map<String, ?> languageCreateProc(String languageName);

    default SimpleStoredProcedureResult delete(Integer id) {
        return StoredProcedureResult.fromMessage(this.languageDeleteProc(id));
    }

    @Procedure(procedureName = "helloworld.deleteLanguage", outputParameterName = "errorMessage_")
    String languageDeleteProc(Integer id);

    default SimpleStoredProcedureResult undelete(Integer id) {
        return StoredProcedureResult.fromMessage(this.languageUndeleteProc(id));
    }

    @Procedure(procedureName = "helloworld.undeleteLanguage", outputParameterName = "errorMessage_")
    String languageUndeleteProc(Integer id);
}
