package com.chao.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Properties;

public class StaticGenerator {
    public static void main(String[] args) {
        //获取项目的根路径
        String projectPath = System.getProperty("user.dir");
        System.out.println("=----=" + projectPath);
        File parentFile = new File(projectPath).getParentFile();

        String inputPath = new File(parentFile, "chao-generator-demo-projects/acm-template").getAbsolutePath();
        System.out.println("=----=" + inputPath);

        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);

    }
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}
