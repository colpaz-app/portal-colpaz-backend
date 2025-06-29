package com.colpaz.colpaz.bannerTranslation.services;

import com.colpaz.colpaz.banner.Banner;
import com.colpaz.colpaz.banner.BannerRepository;
import com.colpaz.colpaz.bannerTranslation.BannerTranslation;
import com.colpaz.colpaz.bannerTranslation.BannerTranslationMapper;
import com.colpaz.colpaz.bannerTranslation.BannerTranslationRepository;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationRequest;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationResponse;
import com.colpaz.colpaz.exceptions.ResourceAlreadyExistsException;
import com.colpaz.colpaz.exceptions.ResourceNotFoundException;
import com.colpaz.colpaz.language.Language;
import com.colpaz.colpaz.language.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerTranslationServiceImpl implements BannerTranslationService {

    private final BannerTranslationRepository repository;
    private final BannerRepository bannerRepository;
    private final LanguageRepository languageRepository;
    private final BannerTranslationMapper mapper;

    public BannerTranslationServiceImpl(
            BannerTranslationRepository repository,
            BannerRepository bannerRepository,
            LanguageRepository languageRepository,
            BannerTranslationMapper mapper
    ) {
        this.repository = repository;
        this.bannerRepository = bannerRepository;
        this.languageRepository = languageRepository;
        this.mapper = mapper;
    }

    @Override
    public BannerTranslationResponse create(BannerTranslationRequest request) {
        if (repository.existsByBannerIdAndLanguageId(request.getBannerId(), request.getLanguageId())) {
            throw new ResourceAlreadyExistsException("Ya existe una traducción para este banner e idioma");
        }

        Banner banner = bannerRepository.findById(request.getBannerId())
                .orElseThrow(() -> new ResourceNotFoundException("Banner no encontrado"));

        Language language = languageRepository.findById(request.getLanguageId())
                .orElseThrow(() -> new ResourceNotFoundException("Idioma no encontrado"));

        BannerTranslation entity = mapper.toEntity(request, banner, language);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    public List<BannerTranslationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BannerTranslationResponse findById(Long id) {
        BannerTranslation entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Traducción no encontrada"));
        return mapper.toResponse(entity);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Traducción no encontrada");
        }
        repository.deleteById(id);
    }
}