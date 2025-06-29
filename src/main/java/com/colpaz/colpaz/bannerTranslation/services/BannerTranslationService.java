package com.colpaz.colpaz.bannerTranslation.services;

import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationRequest;
import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationResponse;

import java.util.List;

public interface BannerTranslationService {
    BannerTranslationResponse create(BannerTranslationRequest request);
    List<BannerTranslationResponse> findAll();
    BannerTranslationResponse findById(Long id);
    void delete(Long id);
}