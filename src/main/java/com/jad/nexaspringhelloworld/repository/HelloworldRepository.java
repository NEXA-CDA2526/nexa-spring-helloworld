package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.HelloworldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HelloworldRepository extends JpaRepository<HelloworldEntity, Integer> {
    @SuppressWarnings("SpringDataMethodInconsistencyInspection")
    @Procedure(procedureName = "helloworld.findHellowordlByIdLanguage")
    Optional<HelloworldEntity> findByIdLanguage(@Param("_idLanguage") Integer idLanguage);

    @SuppressWarnings("SpringDataMethodInconsistencyInspection")
    @Procedure(procedureName = "helloworld.findHellowordlByLanguageName")
    Optional<HelloworldEntity> findByLanguageName(@Param("_languageName") String languageName);
}
