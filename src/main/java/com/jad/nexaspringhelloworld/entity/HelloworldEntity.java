package com.jad.nexaspringhelloworld.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Getter
@Table(name = "helloworld_view", schema = "helloworld")
public class HelloworldEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "value", nullable = false, length = 255)
    private String value;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_language", nullable = false)
    private LanguageEntity languageEntity;
}
