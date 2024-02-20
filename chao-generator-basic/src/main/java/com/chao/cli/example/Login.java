package com.chao.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--username"}, required = true, description = "账号")
    String user;

    @Option(names = {"-p", "--password"}, interactive = true, arity = "0..1", description = "密码")
    String password;

    @Option(names = {"-cp", "--checkPassword"}, interactive = true, arity = "0..1", description = "校验密码")
    String checkPassword;

    @Override
    public Integer call() {
        System.out.println("登录成功" + user + ":" + password + "--" + checkPassword);
        return null;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute(args);
    }
}
