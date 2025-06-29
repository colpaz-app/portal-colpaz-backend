package com.colpaz.colpaz.banner.services;

import com.colpaz.colpaz.banner.Banner;
import com.colpaz.colpaz.banner.BannerMapper;
import com.colpaz.colpaz.banner.BannerRepository;
import com.colpaz.colpaz.banner.dto.BannerRequest;
import com.colpaz.colpaz.banner.dto.BannerResponse;
import com.colpaz.colpaz.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService{

    private final BannerRepository repository;
    private final BannerMapper mapper;

    public BannerServiceImpl(BannerRepository repository, BannerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BannerResponse create(BannerRequest request, String createdBy) {
        Banner banner = mapper.toEntity(request);
        banner.setCreatedBy(createdBy);
        banner.setCreatedAt(LocalDateTime.now());
        banner.setModifiedAt(LocalDateTime.now());
        return mapper.toResponse(repository.save(banner));
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