package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import com.jad.nexaspringhelloworld.repository.result.OperationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends JpaRepository<LanguageEntity, Integer> {
    default OperationResult create(final String name) {
        return OperationResult.fromMessage(this.languageCreateProc(name));
    }

    @Procedure(procedureName = "helloworld.createLanguage", outputParameterName = "errorMessage_")
    String languageCreateProc(@Param("_languageName") String languageName);

    default OperationResult update(Integer id, String name) {
        return OperationResult.fromMessage(this.languageUpdateProc(id, name));
    }

    @Procedure(procedureName = "helloworld.updateLanguage", outputParameterName = "errorMessage_")
    String languageUpdateProc(Integer id, String name);
}
