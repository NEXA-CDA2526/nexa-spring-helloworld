package com.jad.nexaspringhelloworld.mapper;

import com.jad.nexaspringhelloworld.dto.HelloWorldDto;
import com.jad.nexaspringhelloworld.entity.HelloworldEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HelloworldMapper {
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "value", source = "entity.value")
    HelloWorldDto entityToDto(HelloworldEntity entity);
}
