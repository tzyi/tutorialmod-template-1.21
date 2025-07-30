Minecraft 1.21 Fabric 模组开发教程


# 目錄結構

```bash
project-root/
├── gradle/                     # Gradle 包裝器檔案
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src/
│   ├── main/                   # 主要程式碼
│   │   ├── java/              # Java 原始碼
│   │   │   └── com/
│   │   │       └── yourcompany/
│   │   │           └── yourproject/
│   │   │               ├── Application.java
│   │   │               ├── controller/
│   │   │               ├── service/
│   │   │               ├── model/
│   │   │               └── repository/
│   │   └── resources/         # 資源檔案
│   │       ├── application.properties
│   │       ├── static/        # 靜態檔案 (CSS, JS, 圖片)
│   │       └── templates/     # 模板檔案
│   └── test/                  # 測試程式碼
│       ├── java/              # 測試 Java 原始碼
│       │   └── com/
│       │       └── yourcompany/
│       │           └── yourproject/
│       │               └── ApplicationTest.java
│       └── resources/         # 測試資源檔案
├── build/                     # 建置輸出目錄 (自動生成)
├── .gradle/                   # Gradle 快取 (自動生成)
├── build.gradle               # 主要建置腳本
├── settings.gradle            # 專案設定
├── gradlew                    # Gradle 包裝器 (Unix/Linux)
├── gradlew.bat               # Gradle 包裝器 (Windows)
└── README.md                 # 專案說明文件
```

# 常用 Gradle 指令

```bash
./gradlew build #建置專案
./gradlew test #執行測試
./gradlew run #執行應用程式
./gradlew clean #清理建置檔案

```



<br>
# 教程視頻
作者：北山_Besson

教程合集：https://www.bilibili.com/video/BV1gx4y187fX/?vd_source=738b34d8e3ab5a4948957a930a53c8d1