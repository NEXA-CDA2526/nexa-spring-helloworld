package com.jad.nexaspringhelloworld.mapper;

import com.jad.nexaspringhelloworld.dto.LanguageOutput;
import com.jad.nexaspringhelloworld.entity.LanguageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LanguageMapper {
    @Mapping(target = "id", expression = "java(new LanguageId(languageEntity.getId()))")
    @Mapping(target = "data", expression = "java(new LanguageData(languageEntity.getName()))")
    LanguageOutput entityToOutput(LanguageEntity languageEntity);
}
