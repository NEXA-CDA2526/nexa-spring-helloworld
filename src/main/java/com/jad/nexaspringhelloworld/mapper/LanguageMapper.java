package com.jad.nexaspringhelloworld.mapper;

import com.jad.nexaspringhelloworld.dto.LanguageDto;
import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    @Mapping(target = "id", source = "languageEntity.id")
    @Mapping(target = "name", source = "languageEntity.name")
    LanguageDto entityToDto(LanguageEntity languageEntity);
}
