package com.colpaz.colpaz.language;

import com.colpaz.colpaz.language.dto.LanguageRequest;
import com.colpaz.colpaz.language.dto.LanguageResponse;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public Language toEntity(LanguageRequest request) {
        Language language = new Language();
        language.setCode(request.getCode());
        language.setName(request.getName());
        return language;
    }

    public LanguageResponse toResponse(Language language) {
        LanguageResponse response = new LanguageResponse();
        response.setId(language.getId());
        response.setCode(language.getCode());
        response.setName(language.getName());
        response.setCreatedBy(language.getCreatedBy());
        response.setCreatedAt(language.getCreatedAt());
        response.setModifiedAt(language.getModifiedAt());
        return response;
    }
}