package com.chao.maker.generator.file;


import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DynamicFileGenerator {
    public static void doGenerator(Object model, String inputDynamicFilePath, String outputDynamicFilePath) throws IOException, TemplateException {
        //创建 Configuration 对象 参数为 FreeMaker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        //指定模板文本见所在路径
        configuration.setDirectoryForTemplateLoading(new File(inputDynamicFilePath).getParentFile());
        configuration.setDefaultEncoding("UTF-8");
        // 创建模板对象，加载指定模版
        String templateName = new File(inputDynamicFilePath).getName();
        Template template = configuration.getTemplate(templateName);

        //文件不存在则创建文件和目录
        if (!FileUtil.exist(outputDynamicFilePath)) {
            FileUtil.touch(outputDynamicFilePath);
        }

        //生成文件
        Writer out = new FileWriter(outputDynamicFilePath);
        template.process(model, out);
        out.close();
    }
}
