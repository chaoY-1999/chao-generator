package com.chao.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.chao.maker.meta.enums.FileGenerateTypeEnum;
import com.chao.maker.meta.enums.FileTypeEnum;
import com.chao.maker.meta.enums.ModelTypeEnum;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * 源信息校验
 */
public class MetaValidator {

    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);

        validAndFillFileConfig(meta);

        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.Models> models = modelConfig.getModels();
        if (!CollectionUtil.isNotEmpty(models)) {
            return;
        }
        for (Meta.ModelConfig.Models model : models) {
            String fieldName = model.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("未填写 filedName");
            }

            String modelType = model.getType();
            if (StrUtil.isEmpty(modelType)) {
                model.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {

        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }

        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }

        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputPath);
        }

        String type = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(type)) {
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.Files> files = fileConfig.getFiles();
        if (!CollectionUtil.isNotEmpty(files)) {
            return;
        }
        for (Meta.FileConfig.Files file : files) {
            String inputPath = file.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写 inputPath");
            }

            String outputPath = file.getOutputPath();
            if (StrUtil.isEmpty(outputPath)) {
                file.setOutputPath(inputPath);
            }

            String fileType = file.getType();
            if (StrUtil.isBlank(fileType)) {
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    file.setType(FileTypeEnum.DIR.getValue());
                } else {
                    file.setType(FileTypeEnum.FILE.getValue());
                }
            }

            String generateType = file.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                if (inputPath.endsWith(".ftl")) {
                    file.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    file.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        String name = StrUtil.emptyToDefault(meta.getName(), "my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(), "my-我的模板代码生成器");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "chao");
        String basePackage = StrUtil.emptyToDefault(meta.getBasePackage(), "com.chao");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "0.0.1");
        String createTime = String.format(DateUtil.now(), "yyyy-MM-dd HH:mm");

        meta.setName(name);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setDescription(description);
        meta.setCreateTime(createTime);
    }


}
