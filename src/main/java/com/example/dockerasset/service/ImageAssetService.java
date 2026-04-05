package com.example.dockerasset.service;

import com.example.dockerasset.common.PageResponse;
import com.example.dockerasset.dto.ImageAssetDetailResponse;
import com.example.dockerasset.dto.ImageAssetQuery;
import com.example.dockerasset.dto.ImageAssetSaveRequest;

import java.util.Map;

public interface ImageAssetService {

    PageResponse<ImageAssetDetailResponse> page(ImageAssetQuery query);

    ImageAssetDetailResponse detail(Long id);

    Long create(ImageAssetSaveRequest request);

    void update(Long id, ImageAssetSaveRequest request);

    Map<String, Object> metadata();
}
