package com.example.dockerasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("image_asset")
public class ImageAsset {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String assetCode;
    private String imageName;
    private String imageAlias;
    private String harborProject;
    private String harborRepository;
    private String commonTags;
    private String usageDesc;
    private String teamName;
    private String ownerName;
    private String ownerEmail;
    private String status;
    private String reuseLevel;
    private String runtimeType;
    private String baseImage;
    private String contentSummary;
    private String startupDesc;
    private String envDesc;
    private String volumeDesc;
    private String portDesc;
    private String confidenceLevel;
    private LocalDateTime lastVerifiedAt;
    private String lastVerifiedBy;
    private String remark;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private Integer isDeleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAssetCode() { return assetCode; }
    public void setAssetCode(String assetCode) { this.assetCode = assetCode; }
    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }
    public String getImageAlias() { return imageAlias; }
    public void setImageAlias(String imageAlias) { this.imageAlias = imageAlias; }
    public String getHarborProject() { return harborProject; }
    public void setHarborProject(String harborProject) { this.harborProject = harborProject; }
    public String getHarborRepository() { return harborRepository; }
    public void setHarborRepository(String harborRepository) { this.harborRepository = harborRepository; }
    public String getCommonTags() { return commonTags; }
    public void setCommonTags(String commonTags) { this.commonTags = commonTags; }
    public String getUsageDesc() { return usageDesc; }
    public void setUsageDesc(String usageDesc) { this.usageDesc = usageDesc; }
    public String getTeamName() { return teamName; }
    public void setTeamName(String teamName) { this.teamName = teamName; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getOwnerEmail() { return ownerEmail; }
    public void setOwnerEmail(String ownerEmail) { this.ownerEmail = ownerEmail; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReuseLevel() { return reuseLevel; }
    public void setReuseLevel(String reuseLevel) { this.reuseLevel = reuseLevel; }
    public String getRuntimeType() { return runtimeType; }
    public void setRuntimeType(String runtimeType) { this.runtimeType = runtimeType; }
    public String getBaseImage() { return baseImage; }
    public void setBaseImage(String baseImage) { this.baseImage = baseImage; }
    public String getContentSummary() { return contentSummary; }
    public void setContentSummary(String contentSummary) { this.contentSummary = contentSummary; }
    public String getStartupDesc() { return startupDesc; }
    public void setStartupDesc(String startupDesc) { this.startupDesc = startupDesc; }
    public String getEnvDesc() { return envDesc; }
    public void setEnvDesc(String envDesc) { this.envDesc = envDesc; }
    public String getVolumeDesc() { return volumeDesc; }
    public void setVolumeDesc(String volumeDesc) { this.volumeDesc = volumeDesc; }
    public String getPortDesc() { return portDesc; }
    public void setPortDesc(String portDesc) { this.portDesc = portDesc; }
    public String getConfidenceLevel() { return confidenceLevel; }
    public void setConfidenceLevel(String confidenceLevel) { this.confidenceLevel = confidenceLevel; }
    public LocalDateTime getLastVerifiedAt() { return lastVerifiedAt; }
    public void setLastVerifiedAt(LocalDateTime lastVerifiedAt) { this.lastVerifiedAt = lastVerifiedAt; }
    public String getLastVerifiedBy() { return lastVerifiedBy; }
    public void setLastVerifiedBy(String lastVerifiedBy) { this.lastVerifiedBy = lastVerifiedBy; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public Integer getIsDeleted() { return isDeleted; }
    public void setIsDeleted(Integer isDeleted) { this.isDeleted = isDeleted; }
}
