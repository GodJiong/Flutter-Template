fun properties(key: String) = project.findProperty(key).toString()
plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.7.20"
    id("org.jetbrains.intellij") version "1.11.0"
}

group = "com.wj"
version = properties("releaseVersion")

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    // 下载指定的IDEA（运行插件时会启动这个新的IDEA）
    // version.set("2022.1.4")
    // 指定本地已经安装的IDEA
    // version 和 localPath 这两个配置项不能同时指定，只能设置一个。
    localPath.set("/Applications/IntelliJ IDEA CE.app/Contents")
    // IC用于 IDEA Community、IU IDEA Ultimate、RM RubyMine、PY PyCharm、PS PhpStorm 等
    type.set("IC") // Target IDE Platform

    // 相关jetbrains插件地址：https://plugins.jetbrains.com/plugin/9212-flutter/versions/stable
    plugins.set(
        listOf("java", "Dart:${properties("dartVersion")}", "io.flutter:${properties("flutterVersion")}")
    )
    pluginName.set(properties("pluginName"))
}

tasks {
    // Set the JVM compatibility versions
    properties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it
        }
        withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
            kotlinOptions.jvmTarget = it
        }
    }

    patchPluginXml {
        sinceBuild.set("203.4")
        untilBuild.set("231.*")
        // 每次版本更新的说明
        changeNotes.set("Support automatic generation of flutter templates")
    }

    // System.getenv()从系统环境变量里获取值，{@link https://blog.csdn.net/zhulier1124/article/details/107616885}
    // 签名：https://plugins.jetbrains.com/docs/intellij/plugin-signing.html
    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
