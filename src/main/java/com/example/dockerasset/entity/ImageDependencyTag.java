package com.example.dockerasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("image_dependency_tag")
public class ImageDependencyTag {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long imageAssetId;
    private String dependencyName;
    private String dependencyVersion;
    private String dependencyType;
    private String createdBy;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getImageAssetId() { return imageAssetId; }
    public void setImageAssetId(Long imageAssetId) { this.imageAssetId = imageAssetId; }
    public String getDependencyName() { return dependencyName; }
    public void setDependencyName(String dependencyName) { this.dependencyName = dependencyName; }
    public String getDependencyVersion() { return dependencyVersion; }
    public void setDependencyVersion(String dependencyVersion) { this.dependencyVersion = dependencyVersion; }
    public String getDependencyType() { return dependencyType; }
    public void setDependencyType(String dependencyType) { this.dependencyType = dependencyType; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
