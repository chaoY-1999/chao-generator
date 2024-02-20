package com.chao.cli.example.pattern;

public class RemoteControl {
    protected Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute(); // 执行命令
    }
}
