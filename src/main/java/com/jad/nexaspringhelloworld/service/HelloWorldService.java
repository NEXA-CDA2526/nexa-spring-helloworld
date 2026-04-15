package com.jad.nexaspringhelloworld.service;

import com.jad.nexaspringhelloworld.dto.HelloWorldDto;
import com.jad.nexaspringhelloworld.mapper.HelloworldMapper;
import com.jad.nexaspringhelloworld.repository.HelloworldRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HelloWorldService {
    private final HelloworldRepository helloworldRepository;
    private final HelloworldMapper helloworldMapper;

    public HelloWorldService(final HelloworldRepository helloworldRepository, final HelloworldMapper helloworldMapper) {
        this.helloworldRepository = helloworldRepository;
        this.helloworldMapper = helloworldMapper;
    }

    @Transactional(readOnly = true)
    public HelloWorldDto findById(final Integer id) {
        return this.helloworldRepository
                .findById(id)
                .map(this.helloworldMapper::entityToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Helloworld not found for id = " + id));
    }

    public List<HelloWorldDto> findAll() {
        return this.helloworldRepository
                .findAll()
                .stream()
                .map(this.helloworldMapper::entityToDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public HelloWorldDto findByIdLanguage(final Integer idLanguage) {
        return this.helloworldRepository
                .findByIdLanguage(idLanguage)
                .map(this.helloworldMapper::entityToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Helloworld not found for id = " + idLanguage));
    }

    @Transactional(readOnly = true)
    public HelloWorldDto findByLanguageName(final String languageName) {
        return this.helloworldRepository
                .findByLanguageName(languageName)
                .map(this.helloworldMapper::entityToDto)
                .orElseThrow(() -> new RessourceNotFoundException("Helloworld not found for language " + languageName));
    }
}
