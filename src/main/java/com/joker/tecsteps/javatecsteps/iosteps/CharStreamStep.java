package com.joker.tecsteps.javatecsteps.iosteps;

import java.io.*;

public class CharStreamStep {
    public String bufferedReadFile(String filePath){
        File dstFile = new File(filePath);
        if(!dstFile.exists()){
            return "";
        }
        if(!dstFile.canRead()){
            return "";
        }
        try{
            String s ;

            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(dstFile));
            while ((s = reader.readLine()) != null){
                builder.append(s+"\n");
            }
            reader.close();
            System.out.print(builder.toString());
            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void readFromStandardInput(){
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while ((s = reader.readLine()) != null && s.length() != 0){
                System.out.println("s");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void convertSystemOutToPrinterWriter(){
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("hello world");
    }

}