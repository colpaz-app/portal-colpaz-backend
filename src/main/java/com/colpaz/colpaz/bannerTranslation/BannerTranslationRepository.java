package com.colpaz.colpaz.bannerTranslation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BannerTranslationRepository extends JpaRepository<BannerTranslation, Long> {
    Optional<BannerTranslation> findByBannerIdAndLanguageId(Long bannerId, Long languageId);
    boolean existsByBannerIdAndLanguageId(Long bannerId, Long languageId);
}