package com.colpaz.colpaz.bannerTranslation;

import com.colpaz.colpaz.banner.Banner;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationRequest;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationResponse;
import com.colpaz.colpaz.language.Language;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BannerTranslationMapper {

    public BannerTranslation toEntity(BannerTranslationRequest request, Banner banner, Language language) {
        BannerTranslation translation = new BannerTranslation();
        translation.setBanner(banner);
        translation.setLanguage(language);
        translation.setTitle(request.getTitle());
        translation.setDescription(request.getDescription());
        translation.setCreatedBy(request.getCreatedBy());
        translation.setCreatedAt(LocalDateTime.now());
        translation.setModifiedAt(LocalDateTime.now());
        return translation;
    }

    public BannerTranslationResponse toResponse(BannerTranslation entity) {
        BannerTranslationResponse response = new BannerTranslationResponse();
        response.setId(entity.getId());
        response.setBannerId(entity.getBanner().getId());
        response.setLanguageCode(entity.getLanguage().getCode());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setCreatedBy(entity.getCreatedBy());
        response.setCreatedAt(entity.getCreatedAt());
        response.setModifiedAt(entity.getModifiedAt());
        return response;
    }
}