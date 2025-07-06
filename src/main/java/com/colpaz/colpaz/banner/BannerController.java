package com.colpaz.colpaz.banner;

import com.colpaz.colpaz.banner.dto.BannerRequest;
import com.colpaz.colpaz.banner.dto.BannerResponse;
import com.colpaz.colpaz.banner.services.BannerService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banners")
public class BannerController {

    private final BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @PostMapping
    public BannerResponse create(@Valid @RequestBody BannerRequest request,
                                 @AuthenticationPrincipal UserDetails user) {
        return bannerService.create(request, user.getUsername());
    }

    @GetMapping
    public List<BannerResponse> getAll() {
        return bannerService.findAll();
    }

    @GetMapping("/public/banners")
    public List<BannerResponse> getAllPublicBanners() {
        return bannerService.findAll();
    }

    @GetMapping("/{id}")
    public BannerResponse getById(@PathVariable Long id) {
        return bannerService.findById(id);
    }

    @PutMapping("/{id}")
    public BannerResponse update(@PathVariable Long id,
                                 @Valid @RequestBody BannerRequest request) {
        return bannerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bannerService.delete(id);
    }
}