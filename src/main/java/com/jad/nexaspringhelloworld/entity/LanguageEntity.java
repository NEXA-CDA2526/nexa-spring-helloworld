package com.jad.nexaspringhelloworld.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "language.create",
                procedureName = "helloworld.createLanguage",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "_languageName"),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, type = String.class, name = "errorMessage_"),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "id_")
                }
        )
})

@Table(name = "language_view")
public class LanguageEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;
}