package com.chao.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.chao.generator.MainGenerator;
import com.chao.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;
@Command(name = "generate", description = "生成代码", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-l", "--loop"}, arity = "0..1", interactive = true, echo = true, description = "是否循环")
    private boolean loop;

    @Option(names = {"-a", "--author"}, arity = "0..1", interactive = true, echo = true, description = "作者")
    private String author = "chaoY-1999";

    @Option(names = {"-o", "--outputText"}, arity = "0..1", interactive = true, echo = true, description = "输出文本")
    private String outputText = "sum = ";

    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this, mainTemplateConfig);
        System.out.println("配置信息：" + mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
