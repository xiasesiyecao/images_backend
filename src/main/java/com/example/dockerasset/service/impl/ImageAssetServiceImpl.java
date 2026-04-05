package com.example.dockerasset.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.dockerasset.common.PageResponse;
import com.example.dockerasset.dto.ImageAssetDetailResponse;
import com.example.dockerasset.dto.ImageAssetQuery;
import com.example.dockerasset.dto.ImageAssetSaveRequest;
import com.example.dockerasset.entity.BuildSource;
import com.example.dockerasset.entity.ImageAsset;
import com.example.dockerasset.entity.ImageDependencyTag;
import com.example.dockerasset.mapper.BuildSourceMapper;
import com.example.dockerasset.mapper.ImageAssetMapper;
import com.example.dockerasset.mapper.ImageDependencyTagMapper;
import com.example.dockerasset.service.ImageAssetService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ImageAssetServiceImpl implements ImageAssetService {

    private final ImageAssetMapper imageAssetMapper;
    private final BuildSourceMapper buildSourceMapper;
    private final ImageDependencyTagMapper dependencyTagMapper;

    public ImageAssetServiceImpl(ImageAssetMapper imageAssetMapper,
                                 BuildSourceMapper buildSourceMapper,
                                 ImageDependencyTagMapper dependencyTagMapper) {
        this.imageAssetMapper = imageAssetMapper;
        this.buildSourceMapper = buildSourceMapper;
        this.dependencyTagMapper = dependencyTagMapper;
    }

    @Override
    public PageResponse<ImageAssetDetailResponse> page(ImageAssetQuery query) {
        Page<ImageAsset> page = new Page<>(query.getCurrent(), query.getSize());
        LambdaQueryWrapper<ImageAsset> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(query.getHarborProject()), ImageAsset::getHarborProject, query.getHarborProject());
        wrapper.eq(StringUtils.hasText(query.getStatus()), ImageAsset::getStatus, query.getStatus());
        wrapper.eq(StringUtils.hasText(query.getOwnerName()), ImageAsset::getOwnerName, query.getOwnerName());
        wrapper.eq(StringUtils.hasText(query.getRuntimeType()), ImageAsset::getRuntimeType, query.getRuntimeType());
        if (StringUtils.hasText(query.getKeyword())) {
            wrapper.and(w -> w.like(ImageAsset::getImageName, query.getKeyword())
                .or().like(ImageAsset::getImageAlias, query.getKeyword())
                .or().like(ImageAsset::getHarborRepository, query.getKeyword())
                .or().like(ImageAsset::getUsageDesc, query.getKeyword()));
        }
        wrapper.orderByDesc(ImageAsset::getUpdatedAt);
        Page<ImageAsset> result = imageAssetMapper.selectPage(page, wrapper);
        List<ImageAssetDetailResponse> records = result.getRecords().stream()
            .map(this::toSimpleView)
            .collect(Collectors.toList());
        return new PageResponse<>(result.getTotal(), records);
    }

    @Override
    public ImageAssetDetailResponse detail(Long id) {
        ImageAsset asset = imageAssetMapper.selectById(id);
        if (asset == null) {
            return null;
        }
        ImageAssetDetailResponse response = toSimpleView(asset);
        response.setBuildSources(loadBuildSources(id));
        response.setDependencies(loadDependencies(id));
        return response;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(ImageAssetSaveRequest request) {
        ImageAsset asset = new ImageAsset();
        fillAsset(asset, request);
        asset.setAssetCode(generateAssetCode());
        imageAssetMapper.insert(asset);
        saveChildren(asset.getId(), request);
        return asset.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Long id, ImageAssetSaveRequest request) {
        ImageAsset existing = imageAssetMapper.selectById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Asset not found: " + id);
        }
        fillAsset(existing, request);
        existing.setUpdatedAt(LocalDateTime.now());
        imageAssetMapper.updateById(existing);
        buildSourceMapper.delete(new QueryWrapper<BuildSource>().eq("image_asset_id", id));
        dependencyTagMapper.delete(new QueryWrapper<ImageDependencyTag>().eq("image_asset_id", id));
        saveChildren(id, request);
    }

    @Override
    public Map<String, Object> metadata() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("statusOptions", List.of("draft", "confirmed", "pending_verify", "deprecated"));
        data.put("reuseLevelOptions", List.of("recommended", "cautious", "not_recommended"));
        data.put("confidenceOptions", List.of("high", "medium", "low"));
        data.put("runtimeOptions", List.of("java", "python", "nodejs", "go", "nginx", "base", "other"));
        data.put("sourceTypeOptions", List.of("dockerfile", "custom_build_config", "ci_config", "shell_script", "other"));
        data.put("dependencyTypeOptions", List.of("runtime", "tool", "middleware", "client", "other"));
        return data;
    }

    private void fillAsset(ImageAsset asset, ImageAssetSaveRequest request) {
        LocalDateTime now = LocalDateTime.now();
        if (asset.getCreatedAt() == null) {
            asset.setCreatedAt(now);
            asset.setCreatedBy(defaultOperator(request.getOwnerName()));
        }
        asset.setImageName(request.getImageName());
        asset.setImageAlias(request.getImageAlias());
        asset.setHarborProject(request.getHarborProject());
        asset.setHarborRepository(request.getHarborRepository());
        asset.setCommonTags(request.getCommonTags());
        asset.setUsageDesc(request.getUsageDesc());
        asset.setTeamName(request.getTeamName());
        asset.setOwnerName(request.getOwnerName());
        asset.setOwnerEmail(request.getOwnerEmail());
        asset.setStatus(request.getStatus());
        asset.setReuseLevel(defaultValue(request.getReuseLevel(), "recommended"));
        asset.setRuntimeType(request.getRuntimeType());
        asset.setBaseImage(request.getBaseImage());
        asset.setContentSummary(request.getContentSummary());
        asset.setStartupDesc(request.getStartupDesc());
        asset.setEnvDesc(request.getEnvDesc());
        asset.setVolumeDesc(request.getVolumeDesc());
        asset.setPortDesc(request.getPortDesc());
        asset.setConfidenceLevel(defaultValue(request.getConfidenceLevel(), "medium"));
        asset.setLastVerifiedAt(request.getLastVerifiedAt());
        asset.setLastVerifiedBy(request.getLastVerifiedBy());
        asset.setRemark(request.getRemark());
        asset.setUpdatedAt(now);
        asset.setUpdatedBy(defaultOperator(request.getOwnerName()));
        asset.setIsDeleted(0);
    }

    private void saveChildren(Long assetId, ImageAssetSaveRequest request) {
        LocalDateTime now = LocalDateTime.now();
        for (ImageAssetSaveRequest.BuildSourceInput input : request.getBuildSources()) {
            BuildSource source = new BuildSource();
            source.setImageAssetId(assetId);
            source.setSourceType(input.getSourceType());
            source.setGitlabProjectUrl(input.getGitlabProjectUrl());
            source.setGitlabProjectName(input.getGitlabProjectName());
            source.setBranchOrTag(input.getBranchOrTag());
            source.setBuildFilePath(input.getBuildFilePath());
            source.setBuildContextPath(input.getBuildContextPath());
            source.setCiFilePath(input.getCiFilePath());
            source.setScriptFilePath(input.getScriptFilePath());
            source.setSourceDesc(input.getSourceDesc());
            source.setCreatedAt(now);
            source.setUpdatedAt(now);
            source.setCreatedBy(defaultOperator(request.getOwnerName()));
            source.setUpdatedBy(defaultOperator(request.getOwnerName()));
            source.setIsDeleted(0);
            buildSourceMapper.insert(source);
        }

        if (request.getDependencies() == null) {
            return;
        }
        for (ImageAssetSaveRequest.DependencyInput input : request.getDependencies()) {
            if (!StringUtils.hasText(input.getDependencyName())) {
                continue;
            }
            ImageDependencyTag dependency = new ImageDependencyTag();
            dependency.setImageAssetId(assetId);
            dependency.setDependencyName(input.getDependencyName());
            dependency.setDependencyVersion(input.getDependencyVersion());
            dependency.setDependencyType(input.getDependencyType());
            dependency.setCreatedBy(defaultOperator(request.getOwnerName()));
            dependency.setCreatedAt(now);
            dependencyTagMapper.insert(dependency);
        }
    }

    private List<ImageAssetDetailResponse.BuildSourceView> loadBuildSources(Long assetId) {
        return buildSourceMapper.selectList(new QueryWrapper<BuildSource>().eq("image_asset_id", assetId)).stream()
            .map(source -> {
                ImageAssetDetailResponse.BuildSourceView view = new ImageAssetDetailResponse.BuildSourceView();
                view.setId(source.getId());
                view.setSourceType(source.getSourceType());
                view.setGitlabProjectUrl(source.getGitlabProjectUrl());
                view.setGitlabProjectName(source.getGitlabProjectName());
                view.setBranchOrTag(source.getBranchOrTag());
                view.setBuildFilePath(source.getBuildFilePath());
                view.setBuildContextPath(source.getBuildContextPath());
                view.setCiFilePath(source.getCiFilePath());
                view.setScriptFilePath(source.getScriptFilePath());
                view.setSourceDesc(source.getSourceDesc());
                return view;
            })
            .collect(Collectors.toList());
    }

    private List<ImageAssetDetailResponse.DependencyView> loadDependencies(Long assetId) {
        return dependencyTagMapper.selectList(new QueryWrapper<ImageDependencyTag>().eq("image_asset_id", assetId)).stream()
            .map(tag -> {
                ImageAssetDetailResponse.DependencyView view = new ImageAssetDetailResponse.DependencyView();
                view.setId(tag.getId());
                view.setDependencyName(tag.getDependencyName());
                view.setDependencyVersion(tag.getDependencyVersion());
                view.setDependencyType(tag.getDependencyType());
                return view;
            })
            .collect(Collectors.toList());
    }

    private ImageAssetDetailResponse toSimpleView(ImageAsset asset) {
        ImageAssetDetailResponse response = new ImageAssetDetailResponse();
        response.setId(asset.getId());
        response.setAssetCode(asset.getAssetCode());
        response.setImageName(asset.getImageName());
        response.setImageAlias(asset.getImageAlias());
        response.setHarborProject(asset.getHarborProject());
        response.setHarborRepository(asset.getHarborRepository());
        response.setCommonTags(asset.getCommonTags());
        response.setUsageDesc(asset.getUsageDesc());
        response.setTeamName(asset.getTeamName());
        response.setOwnerName(asset.getOwnerName());
        response.setOwnerEmail(asset.getOwnerEmail());
        response.setStatus(asset.getStatus());
        response.setReuseLevel(asset.getReuseLevel());
        response.setRuntimeType(asset.getRuntimeType());
        response.setBaseImage(asset.getBaseImage());
        response.setContentSummary(asset.getContentSummary());
        response.setStartupDesc(asset.getStartupDesc());
        response.setEnvDesc(asset.getEnvDesc());
        response.setVolumeDesc(asset.getVolumeDesc());
        response.setPortDesc(asset.getPortDesc());
        response.setConfidenceLevel(asset.getConfidenceLevel());
        response.setLastVerifiedAt(asset.getLastVerifiedAt());
        response.setLastVerifiedBy(asset.getLastVerifiedBy());
        response.setRemark(asset.getRemark());
        response.setBuildSources(new ArrayList<>());
        response.setDependencies(new ArrayList<>());
        return response;
    }

    private String generateAssetCode() {
        return "IMG-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    private String defaultOperator(String ownerName) {
        return StringUtils.hasText(ownerName) ? ownerName : "system";
    }

    private String defaultValue(String value, String fallback) {
        return StringUtils.hasText(value) ? value : fallback;
    }
}
