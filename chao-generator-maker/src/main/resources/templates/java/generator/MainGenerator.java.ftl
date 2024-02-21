package ${basePackage}.generator;

import ${basePackage}.model.DataModel;
import ${basePackage}.generator.DynamicGenerator;
import ${basePackage}.generator.StaticGenerator;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
public class MainGenerator {
     /**
     *   生成
     *
     * @param model 数据类型
     * @throws TemplateException
     * @throws IOException
     *
     */
    public static void doGenerator(Object model) throws IOException, TemplateException {
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath;
        String outputPath;

    <#list fileConfig.files as file>
        inputPath = new File(inputRootPath, "${file.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${file.outputPath}").getAbsolutePath();
    <#if file.generateType == "static">
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
    <#else>
        DynamicGenerator.doGenerator(model, inputPath, outputPath);
    </#if>
    </#list>
    }
}