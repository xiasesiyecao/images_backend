# Docker Image Asset Backend

后端服务，负责管理 Docker 镜像资产、构建来源和关键依赖信息。

当前第一版定位是“镜像资产登记服务”，重点把这条链路沉淀下来：

`Harbor 镜像 -> GitLab 构建来源 -> 镜像内容摘要 -> 维护责任人`

## 技术栈

- Spring Boot 3
- MyBatis Plus
- MySQL 8
- H2
- Maven
- Springdoc OpenAPI

## 当前功能

- 镜像资产分页查询
- 镜像资产详情查询
- 镜像资产新增
- 镜像资产编辑
- 构建来源子表管理
- 关键依赖子表管理
- 元数据下拉接口

## 目录结构

```text
src/main/java/com/example/dockerasset/
  common/      通用响应对象
  config/      MyBatis Plus 配置
  controller/  REST 接口
  dto/         请求和响应对象
  entity/      数据库实体
  mapper/      MyBatis Plus Mapper
  service/     服务接口与实现

src/main/resources/
  application.yml         默认配置（MySQL）
  application-dev.yml     开发配置（H2）
  db/schema.sql           MySQL 建表脚本
  db/schema-h2.sql        H2 建表脚本
```

## 环境要求

- JDK 17
- Maven 3.8+

如果使用 MySQL：

- MySQL 8

## 启动方式

### 1. 开发模式启动（推荐）

开发模式使用内置 H2 文件数据库，不依赖本机 MySQL。

```bash
mvn -Dmaven.repo.local=/home/ubuntu/Docker/.m2/repository spring-boot:run -Dspring-boot.run.profiles=dev -B
```

启动后地址：

- API: `http://127.0.0.1:8080`
- Swagger: `http://127.0.0.1:8080/swagger-ui.html`
- H2 Console: `http://127.0.0.1:8080/h2-console`

### 2. MySQL 模式启动

先创建数据库：

```sql
CREATE DATABASE docker_asset DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
```

执行建表脚本：

```bash
mysql -uroot -proot docker_asset < src/main/resources/db/schema.sql
```

然后修改 [application.yml](/home/ubuntu/Docker/backend/src/main/resources/application.yml) 中的数据库连接信息，再启动：

```bash
mvn -Dmaven.repo.local=/home/ubuntu/Docker/.m2/repository spring-boot:run -B
```

## 打包

```bash
mvn -Dmaven.repo.local=/home/ubuntu/Docker/.m2/repository clean package -B
```

打包产物：

```text
target/docker-asset-backend-0.0.1-SNAPSHOT.jar
```

## 主要接口

### 分页查询镜像资产

```http
GET /api/assets
```

### 查询镜像资产详情

```http
GET /api/assets/{id}
```

### 新增镜像资产

```http
POST /api/assets
```

### 编辑镜像资产

```http
PUT /api/assets/{id}
```

### 查询元数据选项

```http
GET /api/assets/metadata/options
```

## 数据模型

当前第一版核心表：

- `image_asset`
- `build_source`
- `image_dependency_tag`
- `asset_change_log`

## 当前适用范围

当前后端还不包含：

- GitLab API 同步
- Harbor API 同步
- 用户权限和 SSO
- 构建文件内容快照
- Excel 导入导出

## 后续建议

- 增加构建文件快照表
- 增加变更日志自动记录
- 增加 Harbor 仓库同步任务
- 增加 GitLab 项目扫描和候选文件推荐
