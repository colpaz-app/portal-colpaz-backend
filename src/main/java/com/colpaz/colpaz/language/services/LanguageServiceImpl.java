package com.colpaz.colpaz.language.services;

import com.colpaz.colpaz.exceptions.ResourceNotFoundException;
import com.colpaz.colpaz.language.Language;
import com.colpaz.colpaz.language.LanguageMapper;
import com.colpaz.colpaz.language.LanguageRepository;
import com.colpaz.colpaz.language.dto.LanguageRequest;
import com.colpaz.colpaz.language.dto.LanguageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository repository;
    private final LanguageMapper mapper;

    public LanguageServiceImpl(LanguageRepository repository, LanguageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LanguageResponse create(LanguageRequest request) {
        if (repository.existsByCode(request.getCode())) {
            throw new ResourceNotFoundException("El idioma ya existe");
        }

        if (repository.count() >= 2) {
            throw new IllegalStateException("Solo se permiten dos idiomas en el sistema");
        }

        Language language = mapper.toEntity(request);
        language.setCreatedBy(request.getCreatedBy());
        language.setCreatedAt(LocalDateTime.now());
        language.setModifiedAt(LocalDateTime.now());

        return mapper.toResponse(repository.save(language));
    }

    @Override
    public List<LanguageResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageResponse findByCode(String code) {
        Language language = repository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Idioma no encontrado"));
        return mapper.toResponse(language);
    }
}