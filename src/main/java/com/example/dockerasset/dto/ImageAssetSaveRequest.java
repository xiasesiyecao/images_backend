package com.example.dockerasset.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;
import java.util.List;

public class ImageAssetSaveRequest {

    private Long id;
    @NotBlank
    private String imageName;
    private String imageAlias;
    @NotBlank
    private String harborProject;
    @NotBlank
    private String harborRepository;
    private String commonTags;
    @NotBlank
    private String usageDesc;
    private String teamName;
    @NotBlank
    private String ownerName;
    private String ownerEmail;
    @NotBlank
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
    @Valid
    @NotEmpty
    private List<BuildSourceInput> buildSources;
    private List<DependencyInput> dependencies;

    public static class BuildSourceInput {
        @NotBlank
        private String sourceType;
        @NotBlank
        private String gitlabProjectUrl;
        private String gitlabProjectName;
        private String branchOrTag;
        @NotBlank
        private String buildFilePath;
        private String buildContextPath;
        private String ciFilePath;
        private String scriptFilePath;
        private String sourceDesc;

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
    }

    public static class DependencyInput {
        @NotBlank
        private String dependencyName;
        private String dependencyVersion;
        private String dependencyType;

        public String getDependencyName() { return dependencyName; }
        public void setDependencyName(String dependencyName) { this.dependencyName = dependencyName; }
        public String getDependencyVersion() { return dependencyVersion; }
        public void setDependencyVersion(String dependencyVersion) { this.dependencyVersion = dependencyVersion; }
        public String getDependencyType() { return dependencyType; }
        public void setDependencyType(String dependencyType) { this.dependencyType = dependencyType; }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public List<BuildSourceInput> getBuildSources() { return buildSources; }
    public void setBuildSources(List<BuildSourceInput> buildSources) { this.buildSources = buildSources; }
    public List<DependencyInput> getDependencies() { return dependencies; }
    public void setDependencies(List<DependencyInput> dependencies) { this.dependencies = dependencies; }
}
