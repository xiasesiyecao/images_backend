package com.example.dockerasset.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

@TableName("build_source")
public class BuildSource {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long imageAssetId;
    private String sourceType;
    private String gitlabProjectUrl;
    private String gitlabProjectName;
    private String branchOrTag;
    private String buildFilePath;
    private String buildContextPath;
    private String ciFilePath;
    private String scriptFilePath;
    private String sourceDesc;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private Integer isDeleted;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getImageAssetId() { return imageAssetId; }
    public void setImageAssetId(Long imageAssetId) { this.imageAssetId = imageAssetId; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public String getGitlabProjectUrl() { return gitlabProjectUrl; }
    public void setGitlabProjectUrl(String gitlabProjectUrl) { this.gitlabProjectUrl = gitlabProjectUrl; }
    public String getGitlabProjectName() { return gitlabProjectName; }
    public void setGitlabProjectName(String gitlabProjectName) { this.gitlabProjectName = gitlabProjectName; }
    public String getBranchOrTag() { return branchOrTag; }
    public void setBranchOrTag(String branchOrTag) { this.branchOrTag = branchOrTag; }
    public String getBuildFilePath() { return buildFilePath; }
    public void setBuildFilePath(String buildFilePath) { this.buildFilePath = buildFilePath; }
    public String getBuildContextPath() { return buildContextPath; }
    public void setBuildContextPath(String buildContextPath) { this.buildContextPath = buildContextPath; }
    public String getCiFilePath() { return ciFilePath; }
    public void setCiFilePath(String ciFilePath) { this.ciFilePath = ciFilePath; }
    public String getScriptFilePath() { return scriptFilePath; }
    public void setScriptFilePath(String scriptFilePath) { this.scriptFilePath = scriptFilePath; }
    public String getSourceDesc() { return sourceDesc; }
    public void setSourceDesc(String sourceDesc) { this.sourceDesc = sourceDesc; }
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
