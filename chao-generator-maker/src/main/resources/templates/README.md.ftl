# ${name}

> ${description}
>
> 作者： ${author}
>
> 项目地址： [chaoY-1999](${gitAddress})

##  使用说明

执行项目目录下的脚本文件

、、、
generator <命令> <选项参数>
        、、、

        事例命令：
        、、、
        generator  generate <#list modelConfig.models as modelInfo>-${modelInfo.abbr}</#list>
        、、、

        ##  参数说明
        <#list modelConfig.models  as modelInfo>
        ${modelInfo?index + 1} ${modelInfo.fieldName}

        类型： ${modelInfo.type}

        描述： ${modelInfo.description}

        默认值： ${modelInfo.defaultValue?c}

        缩写： ${modelInfo.abbr}

</#list>