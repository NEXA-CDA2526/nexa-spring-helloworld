package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.HelloworldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HelloworldRepository extends JpaRepository<HelloworldEntity, Integer> {
    @Procedure(procedureName = "helloworld.findHellowordlByIdLanguage")
    Optional<HelloworldEntity> findByIdLanguage(@Param("_idLanguage") Integer idLanguage);
}
