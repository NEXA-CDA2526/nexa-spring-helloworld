package com.jad.nexaspringhelloworld.controller;

import com.jad.nexaspringhelloworld.command.CommandResult;
import com.jad.nexaspringhelloworld.command.language.LanguageCreateCommand;
import com.jad.nexaspringhelloworld.command.language.LanguageDeleteCommand;
import com.jad.nexaspringhelloworld.command.language.LanguageUndeleteCommand;
import com.jad.nexaspringhelloworld.command.language.LanguageUpdateCommand;
import com.jad.nexaspringhelloworld.dto.LanguageData;
import com.jad.nexaspringhelloworld.dto.LanguageId;
import com.jad.nexaspringhelloworld.dto.LanguageOutput;
import com.jad.nexaspringhelloworld.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/language")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(final LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageOutput> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.languageService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<LanguageOutput>> findAll() {
        return ResponseEntity.ok(this.languageService.findAll());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<LanguageOutput> create(@RequestBody LanguageData languageData) {
        CommandResult<LanguageOutput> commandResult = this.languageService.executeCommand(
                new LanguageCreateCommand(languageData));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                CommandResult.getPayLoadAndThrowIfNull(commandResult, "Create must return a payload."));
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<LanguageOutput> update(@PathVariable Integer id, @RequestBody LanguageData languageData) {
        CommandResult<LanguageOutput> commandResult = this.languageService.executeCommand(
                new LanguageUpdateCommand(new LanguageId(id), languageData));
        return ResponseEntity.ok(
                CommandResult.getPayLoadAndThrowIfNull(commandResult, "Update must return a payload."));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.languageService.executeCommand(new LanguageDeleteCommand(new LanguageId(id)));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> undelete(@PathVariable Integer id) {
        this.languageService.executeCommand(new LanguageUndeleteCommand(new LanguageId(id)));
        return ResponseEntity.noContent().build();
    }
}
