package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import com.jad.nexaspringhelloworld.repository.result.StoredProcedureResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
    default StoredProcedureResult update(Integer id, String name) {
        return StoredProcedureResult.fromMessage(this.languageUpdateProc(id, name));
    }

    @Procedure(procedureName = "helloworld.updateLanguage", outputParameterName = "errorMessage_")
    String languageUpdateProc(Integer id, String name);

    default StoredProcedureResult create(final String name) {
        return StoredProcedureResult.fromMessage(this.languageCreateProc(name));
    }

    @Procedure(procedureName = "helloworld.createLanguage", outputParameterName = "errorMessage_")
    String languageCreateProc(@Param("_languageName") String languageName);

    default StoredProcedureResult delete(Integer id) {
        return StoredProcedureResult.fromMessage(this.languageDeleteProc(id));
    }

    @Procedure(procedureName = "helloworld.deleteLanguage", outputParameterName = "errorMessage_")
    String languageDeleteProc(Integer id);

    default StoredProcedureResult undelete(Integer id) {
        return StoredProcedureResult.fromMessage(this.languageUndeleteProc(id));
    }

    @Procedure(procedureName = "helloworld.undeleteLanguage", outputParameterName = "errorMessage_")
    String languageUndeleteProc(Integer id);
}
