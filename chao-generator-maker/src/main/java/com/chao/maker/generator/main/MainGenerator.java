package com.chao.maker.generator.main;

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

public class MainGenerator extends GenerateTemplate{

    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不要输出 dist 了～");
    }
}
