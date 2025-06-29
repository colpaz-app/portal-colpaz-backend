package com.colpaz.colpaz.banner.services;

import com.colpaz.colpaz.banner.Banner;
import com.colpaz.colpaz.banner.BannerMapper;
import com.colpaz.colpaz.banner.BannerRepository;
import com.colpaz.colpaz.banner.dto.BannerRequest;
import com.colpaz.colpaz.banner.dto.BannerResponse;
import com.colpaz.colpaz.bannerTranslation.BannerTranslation;
import com.colpaz.colpaz.bannerTranslation.BannerTranslationMapper;
import com.colpaz.colpaz.bannerTranslation.BannerTranslationRepository;
import com.colpaz.colpaz.exceptions.ResourceNotFoundException;
import com.colpaz.colpaz.language.LanguageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService{

    private final BannerTranslationRepository translationRepository;
    private final LanguageRepository languageRepository;
    private final BannerTranslationMapper translationMapper;
    private final BannerRepository repository;
    private final BannerMapper mapper;

    public BannerServiceImpl(
            BannerRepository repository,
            BannerMapper mapper,
            BannerTranslationRepository translationRepository,
            LanguageRepository languageRepository,
            BannerTranslationMapper translationMapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.translationRepository = translationRepository;
        this.languageRepository = languageRepository;
        this.translationMapper = translationMapper;
    }

    @Override
    public BannerResponse create(BannerRequest request, String createdBy) {
        Banner banner = mapper.toEntity(request);
        banner.setCreatedBy(createdBy);
        banner.setCreatedAt(LocalDateTime.now());
        banner.setModifiedAt(LocalDateTime.now());

        Banner savedBanner = repository.save(banner);

        if (request.getTranslations() != null) {
            List<BannerTranslation> translations = request.getTranslations().stream()
                    .map(treq -> translationMapper.toEntity(treq, savedBanner,
                            languageRepository.findById(treq.getLanguageId())
                                    .orElseThrow(() -> new ResourceNotFoundException("Idioma no encontrado con ID: " + treq.getLanguageId()))
                    ))
                    .collect(Collectors.toList());

            translationRepository.saveAll(translations);

            savedBanner.setTranslations(translations);
        }

        return mapper.toResponse(savedBanner);
    }

    @Override
    public List<BannerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BannerResponse findById(Long id) {
        Banner banner = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banner no encontrado"));
        return mapper.toResponse(banner);
    }

    @Override
    public BannerResponse update(Long id, BannerRequest request) {
        Banner banner = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Banner no encontrado"));
        banner.setTitle(request.getTitle());
        banner.setImageUrl(request.getImageUrl());
        banner.setLink(request.getLink());
        banner.setStartDate(request.getStartDate());
        banner.setEndDate(request.getEndDate());
        banner.setIsActive(request.getIsActive());
        banner.setModifiedAt(LocalDateTime.now());
        return mapper.toResponse(repository.save(banner));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Banner no encontrado");
        }
        repository.deleteById(id);
    }
}