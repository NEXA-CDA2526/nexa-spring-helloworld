package com.jad.nexaspringhelloworld.repository;

import com.jad.nexaspringhelloworld.entity.HelloworldEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloworldRepository extends JpaRepository<HelloworldEntity, Integer> {
}
