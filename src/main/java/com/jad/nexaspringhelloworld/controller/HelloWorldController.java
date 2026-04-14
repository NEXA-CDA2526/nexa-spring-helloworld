package com.jad.nexaspringhelloworld.controller;

import com.jad.nexaspringhelloworld.dto.HelloWorldDto;
import com.jad.nexaspringhelloworld.service.HelloWorldService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/hello")
public class HelloWorldController {
    private final HelloWorldService helloWorldService;

    public HelloWorldController(final HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HelloWorldDto> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(this.helloWorldService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<HelloWorldDto>> findAll() {
        return ResponseEntity.ok(this.helloWorldService.findAll());
    }

    @GetMapping("/language/{id}")
    public ResponseEntity<HelloWorldDto> findByIdLanguage(@PathVariable Integer id) {
        return ResponseEntity.ok(this.helloWorldService.findByIdLanguage(id));
    }
}
