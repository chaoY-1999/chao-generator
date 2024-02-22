package com.chao.maker.meta.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FileTypeEnum {

    DIR("目录", "dir"),
    FILE("文件", "file");

    private final String text;
    private final String value;

}
