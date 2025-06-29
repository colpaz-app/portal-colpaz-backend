package com.colpaz.colpaz.banner;

import com.colpaz.colpaz.banner.dto.BannerRequest;
import com.colpaz.colpaz.banner.dto.BannerResponse;
import org.springframework.stereotype.Component;

@Component
public class BannerMapper {

    public Banner toEntity(BannerRequest request) {
        Banner banner = new Banner();
        banner.setTitle(request.getTitle());
        banner.setImageUrl(request.getImageUrl());
        banner.setLink(request.getLink());
        banner.setStartDate(request.getStartDate());
        banner.setEndDate(request.getEndDate());
        banner.setIsActive(request.getIsActive());
        return banner;
    }

    public BannerResponse toResponse(Banner banner) {
        BannerResponse response = new BannerResponse();
        response.setId(banner.getId());
        response.setTitle(banner.getTitle());
        response.setImageUrl(banner.getImageUrl());
        response.setLink(banner.getLink());
        response.setStartDate(banner.getStartDate());
        response.setEndDate(banner.getEndDate());
        response.setIsActive(banner.getIsActive());
        response.setCreatedBy(banner.getCreatedBy());
        response.setCreatedAt(banner.getCreatedAt());
        response.setModifiedAt(banner.getModifiedAt());
        return response;
    }
}