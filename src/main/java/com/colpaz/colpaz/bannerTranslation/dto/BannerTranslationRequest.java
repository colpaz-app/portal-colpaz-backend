package com.colpaz.colpaz.bannerTranslation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannerTranslationRequest {

    private Long bannerId;

    @NotNull(message = "El ID del idioma es obligatorio")
    private Long languageId;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede exceder los 255 caracteres")
    private String title;

    private String description;

    @NotBlank(message = "El campo createdBy es obligatorio")
    private String createdBy;
}