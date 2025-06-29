package com.colpaz.colpaz.language.services;

import com.colpaz.colpaz.language.dto.LanguageRequest;
import com.colpaz.colpaz.language.dto.LanguageResponse;

import java.util.List;

public interface LanguageService {
    LanguageResponse create(LanguageRequest request);
    List<LanguageResponse> findAll();
    LanguageResponse findByCode(String code);
}