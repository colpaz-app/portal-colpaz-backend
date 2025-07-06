package com.colpaz.colpaz.banner.dto;

import com.colpaz.colpaz.bannerTranslation.dto.BannerTranslationResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BannerResponse {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String link;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;

    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private List<BannerTranslationResponse> translations;
}