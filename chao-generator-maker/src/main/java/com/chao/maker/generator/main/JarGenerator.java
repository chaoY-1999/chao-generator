package com.chao.maker.generator.main;

import java.io.*;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        String winMavenCommand  = "mvn.cmd clean package -DskipTests";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = otherMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();


        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("Exit Code: " + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("/Users/chao/chao/custom-project/chao-generator/chao-generator-maker/generated/acm-template-pro-generator");
    }
}
