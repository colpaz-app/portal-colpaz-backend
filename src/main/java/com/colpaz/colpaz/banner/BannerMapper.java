package com.colpaz.colpaz.banner;

import com.colpaz.colpaz.banner.dto.BannerRequest;
import com.colpaz.colpaz.banner.dto.BannerResponse;
import com.colpaz.colpaz.bannerTranslation.BannerTranslationMapper;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BannerMapper {

    private final BannerTranslationMapper translationMapper;

    public BannerMapper(BannerTranslationMapper translationMapper) {
        this.translationMapper = translationMapper;
    }

    public Banner toEntity(BannerRequest request) {
        Banner banner = new Banner();
        banner.setTitle(request.getTitle());
        banner.setDescription(request.getDescription());
        banner.setImageUrl(request.getImageUrl());
        banner.setLink(request.getLink());
        banner.setStartDate(request.getStartDate());
        banner.setEndDate(request.getEndDate());
        banner.setIsActive(request.getIsActive());

        return banner;
    }

    public BannerResponse toResponse(Banner banner) {
        BannerResponse response = new BannerResponse();
        response.setId(banner.getId());
        response.setTitle(banner.getTitle());
        response.setDescription(banner.getDescription());
        response.setImageUrl(banner.getImageUrl());
        response.setLink(banner.getLink());
        response.setStartDate(banner.getStartDate());
        response.setEndDate(banner.getEndDate());
        response.setIsActive(banner.getIsActive());
        response.setCreatedAt(banner.getCreatedAt());
        response.setModifiedAt(banner.getModifiedAt());
        response.setCreatedBy(banner.getCreatedBy());

        if (banner.getTranslations() != null) {
            List<BannerTranslationResponse> translations = banner.getTranslations().stream()
                    .map(translationMapper::toResponse)
                    .collect(Collectors.toList());
            response.setTranslations(translations);
        }

        return response;
    }
}