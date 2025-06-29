package com.colpaz.colpaz.language;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByCode(String code);
    Optional<Language> findByCode(String code);
}