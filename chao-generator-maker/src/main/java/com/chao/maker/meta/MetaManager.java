package com.chao.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

import java.util.ResourceBundle;

public class MetaManager {

    private static volatile Meta meta;

    public MetaManager() {
    }

    public static Meta getMetaObject() {
        if (meta == null) {
            synchronized (MetaManager.class) {
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta newMeta = JSONUtil.toBean(metaJson, Meta.class);
        MetaValidator.doValidAndFill(newMeta);
        Meta.FileConfig fileConfig = newMeta.getFileConfig();
        //TODO 校验和处理默认值
        return newMeta;

    }
}

