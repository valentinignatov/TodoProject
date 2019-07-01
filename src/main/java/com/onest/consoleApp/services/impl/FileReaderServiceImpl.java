package com.onest.consoleApp.services.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.onest.consoleApp.services.FileReaderService;

import java.io.*;
import java.io.File;
import java.util.Scanner;

public class FileReaderServiceImpl implements FileReaderService {

    public String read(){
        String path = "/home/valentin/Downloads/consoleApp/src/main/resources/query.txt";

        File file = new File(path);
        String result = "";

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()){
            result = result.concat(scanner.nextLine());
        };

        scanner.close();
        return result;
    }
}
