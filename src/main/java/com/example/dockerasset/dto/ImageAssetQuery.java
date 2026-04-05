package com.example.dockerasset.dto;

public class ImageAssetQuery {

    private long current = 1;
    private long size = 10;
    private String keyword;
    private String harborProject;
    private String status;
    private String ownerName;
    private String runtimeType;

    public long getCurrent() { return current; }
    public void setCurrent(long current) { this.current = current; }
    public long getSize() { return size; }
    public void setSize(long size) { this.size = size; }
    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }
    public String getHarborProject() { return harborProject; }
    public void setHarborProject(String harborProject) { this.harborProject = harborProject; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public String getRuntimeType() { return runtimeType; }
    public void setRuntimeType(String runtimeType) { this.runtimeType = runtimeType; }
}
