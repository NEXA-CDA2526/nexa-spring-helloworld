package com.jad.nexaspringhelloworld.service;

import com.jad.nexaspringhelloworld.dto.LanguageDto;
import com.jad.nexaspringhelloworld.mapper.LanguageMapper;
import com.jad.nexaspringhelloworld.repository.LanguageRepository;
import com.jad.nexaspringhelloworld.repository.result.OperationResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public LanguageService(final LanguageRepository languageRepository, final LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Transactional(readOnly = true)
    public List<LanguageDto> findAll() {
        return this.languageRepository
                .findAll()
                .stream()
                .map(this.languageMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public LanguageDto findById(final Integer id) {
        return this.languageRepository
                .findById(id)
                .map(this.languageMapper::entityToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Language not found: " + id));
    }

    @Transactional
    public void create(final String name) {
        final OperationResult operationResult = this.languageRepository.create(name);
        if (!operationResult.success()) throw new ServiceOperationException(operationResult.message());
    }
}
