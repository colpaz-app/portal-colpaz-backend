package com.colpaz.colpaz.banner.services;

import com.colpaz.colpaz.banner.dto.BannerRequest;
import com.colpaz.colpaz.banner.dto.BannerResponse;

import java.util.List;

public interface BannerService {
    BannerResponse create(BannerRequest request, String createdBy);
    List<BannerResponse> findAll();
    BannerResponse findById(Long id);
    BannerResponse update(Long id, BannerRequest request);
    void delete(Long id);
}