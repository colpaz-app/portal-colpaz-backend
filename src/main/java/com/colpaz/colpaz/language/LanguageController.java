package com.colpaz.colpaz.language;

import com.colpaz.colpaz.language.dto.LanguageRequest;
import com.colpaz.colpaz.language.dto.LanguageResponse;
import com.colpaz.colpaz.language.services.LanguageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @PostMapping
    public LanguageResponse create(@Valid @RequestBody LanguageRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<LanguageResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{code}")
    public LanguageResponse findByCode(@PathVariable String code) {
        return service.findByCode(code);
    }
}