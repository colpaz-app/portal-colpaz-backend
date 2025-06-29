package com.colpaz.colpaz.bannerTranslation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerTranslationRepository extends JpaRepository<BannerTranslation, Long> {
    boolean existsByBannerIdAndLanguageId(Long bannerId, Long languageId);
}