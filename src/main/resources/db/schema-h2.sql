CREATE TABLE IF NOT EXISTS image_asset (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    asset_code VARCHAR(64) NOT NULL UNIQUE,
    image_name VARCHAR(255) NOT NULL,
    image_alias VARCHAR(255),
    harbor_project VARCHAR(255) NOT NULL,
    harbor_repository VARCHAR(255) NOT NULL,
    common_tags VARCHAR(500),
    usage_desc TEXT,
    team_name VARCHAR(255),
    owner_name VARCHAR(100) NOT NULL,
    owner_email VARCHAR(255),
    status VARCHAR(32) NOT NULL DEFAULT 'draft',
    reuse_level VARCHAR(32) DEFAULT 'recommended',
    runtime_type VARCHAR(64),
    base_image VARCHAR(255),
    content_summary TEXT,
    startup_desc TEXT,
    env_desc TEXT,
    volume_desc TEXT,
    port_desc VARCHAR(255),
    confidence_level VARCHAR(32) DEFAULT 'medium',
    last_verified_at TIMESTAMP NULL,
    last_verified_by VARCHAR(100),
    remark TEXT,
    created_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_deleted TINYINT NOT NULL DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_image_name ON image_asset (image_name);
CREATE INDEX IF NOT EXISTS idx_harbor_repo ON image_asset (harbor_project, harbor_repository);
CREATE INDEX IF NOT EXISTS idx_owner_name ON image_asset (owner_name);
CREATE INDEX IF NOT EXISTS idx_status ON image_asset (status);
CREATE INDEX IF NOT EXISTS idx_runtime_type ON image_asset (runtime_type);

CREATE TABLE IF NOT EXISTS build_source (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    image_asset_id BIGINT NOT NULL,
    source_type VARCHAR(32) NOT NULL,
    gitlab_project_url VARCHAR(500) NOT NULL,
    gitlab_project_name VARCHAR(255),
    branch_or_tag VARCHAR(255),
    build_file_path VARCHAR(500) NOT NULL,
    build_context_path VARCHAR(500),
    ci_file_path VARCHAR(500),
    script_file_path VARCHAR(500),
    source_desc TEXT,
    created_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_deleted TINYINT NOT NULL DEFAULT 0
);

CREATE INDEX IF NOT EXISTS idx_build_source_asset_id ON build_source (image_asset_id);
CREATE INDEX IF NOT EXISTS idx_build_source_project_name ON build_source (gitlab_project_name);
CREATE INDEX IF NOT EXISTS idx_build_source_type ON build_source (source_type);

CREATE TABLE IF NOT EXISTS image_dependency_tag (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    image_asset_id BIGINT NOT NULL,
    dependency_name VARCHAR(100) NOT NULL,
    dependency_version VARCHAR(100),
    dependency_type VARCHAR(64),
    created_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_dependency_asset_id ON image_dependency_tag (image_asset_id);
CREATE INDEX IF NOT EXISTS idx_dependency_name ON image_dependency_tag (dependency_name);

CREATE TABLE IF NOT EXISTS asset_change_log (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    image_asset_id BIGINT NOT NULL,
    operation_type VARCHAR(32) NOT NULL,
    changed_fields TEXT,
    change_remark TEXT,
    created_by VARCHAR(100) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE INDEX IF NOT EXISTS idx_change_log_asset_id ON asset_change_log (image_asset_id);
