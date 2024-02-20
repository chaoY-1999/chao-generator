package com.chao.generator;


import com.chao.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        String projectPath = System.getProperty("user.dir");
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates.MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "MainTemplate.java";
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("chaoY-1999");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");
        doGenerator(mainTemplateConfig, inputDynamicFilePath, outputDynamicFilePath);
    }

    public static void doGenerator(Object model, String inputDynamicFilePath, String outputDynamicFilePath) throws IOException, TemplateException {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDirectoryForTemplateLoading(new File(inputDynamicFilePath).getParentFile());
        configuration.setDefaultEncoding("UTF-8");


        String templateName = new File(inputDynamicFilePath).getName();
        Template template = configuration.getTemplate(templateName);

        Writer out = new FileWriter(outputDynamicFilePath);
        template.process(model, out);
        out.close();
    }
}
