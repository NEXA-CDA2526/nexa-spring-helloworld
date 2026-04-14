package com.jad.nexaspringhelloworld.controller;

import com.jad.nexaspringhelloworld.dto.LanguageDto;
import com.jad.nexaspringhelloworld.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/language")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<LanguageDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.languageService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> findAll() {
        return ResponseEntity.ok(this.languageService.findAll());
    }
}
