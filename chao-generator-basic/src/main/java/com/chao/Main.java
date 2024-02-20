package com.chao;

import com.chao.cli.CommandExecutor;

//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击间距中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {
//        String[] myargs = new String[] {"config"};
//        String[] myargs = new String[] {"list"};
//        String[] myargs = new String[] {"generate", "-l", "-a", "-o"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}