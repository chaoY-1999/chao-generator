package com.chao.generator;

import com.chao.model.MainTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("chaoY-1999");
        mainTemplateConfig.setLoop(true);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerator(mainTemplateConfig);

    }

//    public static void doGenerator(Object model) throws TemplateException, IOException {
//        String projectPath = System.getProperty("user.dir");
//
//        File parentFile = new File(projectPath).getParentFile();
//
//        String inputPath = new File(parentFile, "chao-generator-demo-projects/acm-template").getAbsolutePath();
//
//        String outputPath = projectPath;
//        System.out.println("parentFile: "+ parentFile);
//        System.out.println("inputPath: "+ inputPath);
//        System.out.println("outputPath: "+ outputPath);
//        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
//
//        String inputDynamicFilePath = outputPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
//        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/main/java/com/chao/generator/MainTemplate.java";
//        System.out.println("===================");
//        System.out.println("inputDynamicFilePath: "+ inputDynamicFilePath);
//        System.out.println("outputDynamicFilePath: "+ outputDynamicFilePath);
//
//        DynamicGenerator.doGenerator(model, inputDynamicFilePath, outputDynamicFilePath);
//    }
    public static void doGenerator(Object model) throws IOException, TemplateException {
        String inputRootPath = "/Users/chao/chao/custom-project/chao-generator/chao-generator-demo-projects/acm-template-pro";
        String outputRootPath = "/Users/chao/chao/custom-project/chao-generator/acm-template-pro";

        String inputPath;
        String outputPath;

        inputPath = new File(inputRootPath, "src/com/chao/acm/MainTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/chao/acm/MainTemplate.java").getAbsolutePath();
        DynamicGenerator.doGenerator(model, inputPath, outputPath);

        inputPath = new File(inputRootPath, ".gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, ".gitignore").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
    }
}
