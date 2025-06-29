package com.colpaz.colpaz.language.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LanguageRequest {

    @NotBlank(message = "El código del idioma es obligatorio")
    @Size(min = 2, max = 2, message = "El código debe tener exactamente 2 caracteres")
    @Pattern(regexp = "^(ES|EN)$", message = "Solo se permiten los idiomas ES o EN")
    private String code;

    @NotBlank(message = "El nombre del idioma es obligatorio")
    @Size(max = 50, message = "El nombre del idioma no puede superar los 50 caracteres")
    private String name;

    @NotBlank(message = "El campo 'createdBy' es obligatorio")
    @Size(max = 100, message = "El nombre del creador no puede superar los 100 caracteres")
    private String createdBy;
}