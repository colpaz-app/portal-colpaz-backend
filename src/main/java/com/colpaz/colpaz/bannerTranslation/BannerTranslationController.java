package com.colpaz.colpaz.bannerTranslation;

import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationRequest;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationResponse;
import com.colpaz.colpaz.bannerTranslation.services.BannerTranslationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banner-translations")
public class BannerTranslationController {

    private final BannerTranslationService service;

    public BannerTranslationController(BannerTranslationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BannerTranslationResponse> create(@Valid @RequestBody BannerTranslationRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<BannerTranslationResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BannerTranslationResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}