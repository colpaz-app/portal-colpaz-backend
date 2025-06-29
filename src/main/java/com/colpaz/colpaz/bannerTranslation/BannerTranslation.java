package com.colpaz.colpaz.bannerTranslation;

import com.colpaz.colpaz.banner.Banner;
import com.colpaz.colpaz.language.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "banner_translations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"banner_id", "language_id"})
})
public class BannerTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id")
    private Language language;

    @NotBlank
    @Size(max = 255)
    private String title;

    private String description;

    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}