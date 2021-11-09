package org.university.security_tasks;

import lombok.extern.log4j.Log4j;
import org.apache.log4j.BasicConfigurator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Log4j
public class Main {

    public static void main(String[] args) {
        BasicConfigurator.configure();

        String input = getBinaryString(readTextFromFile("/input.txt"));
        String input_salt = readTextFromFile("/input_salt.txt").toString();

        char [] chars = new char[input_salt.length()];
        input_salt.getChars(0, input_salt.length(), chars, 0);

        StringBuilder res = new StringBuilder();
        int i = 0;
        for (char ch : chars) {
            if (i >= input.length()){
                res.append(ch);
            }
            else {
                char inputCh = input.charAt(i);
                if (ch == ' '){
                    if (inputCh == '1'){
                        res.append("  ");
                    }
                    else if (inputCh == '0'){
                        res.append(" ");
                    }
                    i++;
                }
                else {
                    res.append(ch);
                }
            }
        }

        writeToFile(res.toString(), "/output.txt");
    }


    public static StringBuilder readTextFromFile(String fileName){
        String path = System.getenv("RESOURCES_PATH");
        StringBuilder str = new StringBuilder();
        try(FileInputStream fileInputStream = new FileInputStream(path + fileName)){
            int i;
            while((i = fileInputStream.read()) != -1){
                str.append((char) i);
            }
        }
        catch (IOException e){
            log.error("error while parsing the file");
        }

        return str;
    }

    public static String getBinaryString(StringBuilder str){
        StringBuilder binaryStr = new StringBuilder();
        for (int b: str.toString().getBytes()) {
            binaryStr.append(Integer.toBinaryString(b));
        }
        return binaryStr.toString();
    }

    public static void writeToFile(String str, String fileName){
        String path = System.getenv("RESOURCES_PATH");
        try(FileOutputStream out = new FileOutputStream(path + fileName)){
            out.write(str.getBytes());
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
