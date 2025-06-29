package com.colpaz.colpaz.banner.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BannerRequest {
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede tener más de 100 caracteres")
    private String title;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Size(max = 255, message = "La URL de la imagen no puede tener más de 255 caracteres")
    @Pattern(regexp = "^(http|https)://.*$", message = "Debe ser una URL válida")
    private String imageUrl;

    @Size(max = 255, message = "El enlace no puede tener más de 255 caracteres")
    @Pattern(regexp = "^(http|https)://.*$", message = "Debe ser una URL válida")
    private String link;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private LocalDate endDate;

    @NotNull(message = "El estado activo/inactivo es obligatorio")
    private Boolean isActive;
}