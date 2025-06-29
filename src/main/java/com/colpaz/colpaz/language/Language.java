package com.colpaz.colpaz.language;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "languages")
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 2)
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^(ES|EN)$", message = "Solo se permiten los idiomas ES o EN")
    private String code;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String createdBy;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}