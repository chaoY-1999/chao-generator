package com.chao.maker.generator.main;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.chao.maker.generator.file.DynamicFileGenerator;
import com.chao.maker.generator.file.StaticFileGenerator;
import com.chao.maker.meta.Meta;
import com.chao.maker.meta.MetaManager;
import freemarker.template.TemplateException;
import freemarker.template.utility.StringUtil;

import java.io.File;
import java.io.IOException;

public abstract class GenerateTemplate {
    
    public void doGenerate() throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();

        //0-获取根路径
        String projectPath = System.getProperty("user.dir");
        String outputPath = projectPath + File.separator + "generated" + File.separator + meta.getName();
        if (!FileUtil.exist((outputPath))) {
            FileUtil.mkdir(outputPath);
        }

        //1-复制源文件
        String sourceCopyDestPath = copySource(meta, outputPath);


        //2-代码生成
        generateCode(meta, outputPath);

        //3-构建jar包
        String jarPath = buildJar(meta, outputPath);

        //4-封装脚本
        String shellOutputFilePath = buildScript(outputPath, jarPath);

        //5-生成精简版 jar包
        buildDist(outputPath, sourceCopyDestPath, jarPath, shellOutputFilePath);



    }

    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        String distOutputPath =  outputPath + "-dest";
        //拷贝 -jar 包
        String targetAbsolutePath = distOutputPath + File.separator + "target";
        FileUtil.mkdir(targetAbsolutePath);
        String jarAbsolutePath = outputPath + File.separator + jarPath;
        FileUtil.copy(jarAbsolutePath, targetAbsolutePath, true);
        //拷贝 脚本 文件
        FileUtil.copy(shellOutputFilePath, distOutputPath, true);
        FileUtil.copy(shellOutputFilePath + ".bat", distOutputPath, true);
        //拷贝 源版本 文件
        FileUtil.copy(sourceCopyDestPath, distOutputPath, true);
    }

    protected String buildScript(String outputPath, String jarPath) {
        String shellOutputFilePath = outputPath + File.separator + "generator";
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
        return shellOutputFilePath;
    }

    protected String buildJar(Meta meta, String outputPath) throws IOException, InterruptedException {
        JarGenerator.doGenerate(outputPath);
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/"+ jarName;
        return jarPath;
    }


    protected String copySource(Meta meta, String outputPath) {
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyDestPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyDestPath, false);
        return sourceCopyDestPath;
    }


    protected void generateCode(Meta meta, String outputPath) throws TemplateException, IOException, InterruptedException {
        //读取 resources 目录下的所有文件
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();

        // java 包基础路径
        String outputBasePackage = meta.getBasePackage();
        String outputBasePackagePath = String.join("/", StringUtil.split(outputBasePackage, '.'));
        String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java/"+ outputBasePackagePath;
        String inputFilePath;
        String outputFilePath;

        //cli.command.ConfigCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //cli.command.GenerateCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //cli.command.ListCommand
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //cli.command.CommandExecutor
        inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //generator.DynamicGenerator
        inputFilePath = inputResourcePath + "/templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/DynamicGenerator.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //generator.MainGenerator
        inputFilePath = inputResourcePath + "/templates/java/generator/MainGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/MainGenerator.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //generator.StaticGenerator
        inputFilePath = inputResourcePath + "/templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/generator/StaticGenerator.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //model.dataModel
        inputFilePath = inputResourcePath + "/templates/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/model/DataModel.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        //main
        inputFilePath = inputResourcePath + "/templates/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPackagePath + File.separator + "/Main.java";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);

        // pom.xml
        inputFilePath = inputResourcePath + "/templates/pom.xml.ftl";
        outputFilePath = outputPath + File.separator + "/pom.xml";
        DynamicFileGenerator.doGenerator(meta, inputFilePath, outputFilePath);


        // git init
        ProcessBuilder processBuilder = new ProcessBuilder("git", "init");
        processBuilder.directory(new File(outputPath));
        // 生成 .gitignore -- outputPath
        outputFilePath = outputPath + File.separator + ".gitignore";
        StaticFileGenerator.copyFilesByHutool(inputFilePath, outputFilePath);
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        System.out.println("git init---" + exitCode);
    }
}
