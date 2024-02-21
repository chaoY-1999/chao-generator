package ${basePackage};

import ${basePackage}.cli.CommandExecutor;

public class Main {
public static void main(String[] args) {
//        String[] myargs = new String[] {"config"};
//        String[] myargs = new String[] {"list"};
//        String[] myargs = new String[] {"generate", "-l", "-a", "-o"};
CommandExecutor commandExecutor = new CommandExecutor();
commandExecutor.doExecute(args);
}
}