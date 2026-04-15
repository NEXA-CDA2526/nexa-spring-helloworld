package com.jad.nexaspringhelloworld.controller;

import com.jad.nexaspringhelloworld.dto.LanguageDto;
import com.jad.nexaspringhelloworld.dto.request.LanguageCreateRequest;
import com.jad.nexaspringhelloworld.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @PostMapping(path = "/add", consumes = "application/json")
    public ResponseEntity<Void> create(@RequestBody LanguageCreateRequest languageCreateRequest) {
        this.languageService.create(languageCreateRequest.name());
        return ResponseEntity.created(URI.create("api/language/add")).build();
    }

//    @PostMapping(path = "/update", consumes = "application/json")
//    public ResponseEntity<Void> create(@RequestBody LanguageDto languageDto) {
//        this.languageService.update(languageDto.id(), languageDto.name());
//        return ResponseEntity.created(URI.create("api/language/add")).build();
//    }
}
