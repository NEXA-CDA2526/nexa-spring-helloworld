package com.jad.nexaspringhelloworld.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
