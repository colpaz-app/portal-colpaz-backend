package com.colpaz.colpaz.language.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LanguageResponse {
    private Long id;
    private String code;
    private String name;
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}