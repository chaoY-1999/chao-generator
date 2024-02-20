package com.chao.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable{

    @Override
    public void run() {
        System.out.println("执行中...");
    }

    @Command(name = "add", description = "增加", mixinStandardHelpOptions = true)
    static class AddSubCommandExample implements Runnable{
        public void run() {
            System.out.println("执行add子命令...");
        }
    }

    @Command(name = "delete", description = "删除", mixinStandardHelpOptions = true)
    static class DeleteSubCommandExample implements Runnable{
        public void run() {
            System.out.println("执行delete子命令...");
        }
    }

    @Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class QuerySubCommandExample implements Runnable{
        public void run() {
            System.out.println("执行query子命令...");
        }
    }

    public static void main(String[] args) {
        String[] myArgs = new String[] {"add", "--help"};
        int exitCode = new CommandLine(new SubCommandExample())
                .addSubcommand(new AddSubCommandExample())
                .addSubcommand(new DeleteSubCommandExample())
                .addSubcommand(new QuerySubCommandExample())
                .execute(myArgs);

        System.exit(exitCode);

    }
}
