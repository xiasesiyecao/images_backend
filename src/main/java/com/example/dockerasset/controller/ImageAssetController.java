package com.example.dockerasset.controller;

import com.example.dockerasset.common.ApiResponse;
import com.example.dockerasset.common.PageResponse;
import com.example.dockerasset.dto.ImageAssetDetailResponse;
import com.example.dockerasset.dto.ImageAssetQuery;
import com.example.dockerasset.dto.ImageAssetSaveRequest;
import com.example.dockerasset.service.ImageAssetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class ImageAssetController {

    private final ImageAssetService imageAssetService;

    public ImageAssetController(ImageAssetService imageAssetService) {
        this.imageAssetService = imageAssetService;
    }

    @GetMapping
    public ApiResponse<PageResponse<ImageAssetDetailResponse>> page(ImageAssetQuery query) {
        return ApiResponse.success(imageAssetService.page(query));
    }

    @GetMapping("/{id}")
    public ApiResponse<ImageAssetDetailResponse> detail(@PathVariable Long id) {
        return ApiResponse.success(imageAssetService.detail(id));
    }

    @PostMapping
    public ApiResponse<Long> create(@Valid @RequestBody ImageAssetSaveRequest request) {
        return ApiResponse.success(imageAssetService.create(request), "Created");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id, @Valid @RequestBody ImageAssetSaveRequest request) {
        imageAssetService.update(id, request);
        return ApiResponse.success(null, "Updated");
    }

    @GetMapping("/metadata/options")
    public ApiResponse<Map<String, Object>> metadata() {
        return ApiResponse.success(imageAssetService.metadata());
    }
}
