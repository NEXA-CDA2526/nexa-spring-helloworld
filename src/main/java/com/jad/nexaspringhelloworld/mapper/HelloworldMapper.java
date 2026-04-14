package com.jad.nexaspringhelloworld.mapper;

import com.jad.nexaspringhelloworld.dto.HelloWorldDto;
import com.jad.nexaspringhelloworld.entity.HelloworldEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HelloworldMapper {
    @Mapping(target = "id", source = "helloworldEntity.id")
    @Mapping(target = "value", source = "helloworldEntity.value")
    @Mapping(target = "idLanguage", source = "helloworldEntity.languageEntity.id")
    HelloWorldDto entityToDto(HelloworldEntity helloworldEntity);
}
