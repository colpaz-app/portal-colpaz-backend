package com.colpaz.colpaz.bannerTranslation.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BannerTranslationResponse {
    private Long id;
    private Long bannerId;
    private String languageCode;
    private String title;
    private String description;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}