package com.chao.maker.generator.file;

import com.chao.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class FileGenerator {
    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("chaoY-1999");
        dataModel.setLoop(true);
        dataModel.setOutputText("求和结果：");
        doGenerator(dataModel);

    }

    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");

        File parentFile = new File(projectPath).getParentFile();

        String inputPath = new File(parentFile, "chao-generator-demo-projects/acm-template").getAbsolutePath();

        String outputPath = projectPath;
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);

        String inputDynamicFilePath = outputPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/main/java/com/chao/generator/MainTemplate.java";

        DynamicFileGenerator.doGenerator(model, inputDynamicFilePath, outputDynamicFilePath);
    }
}
