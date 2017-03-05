package com.joker.tecsteps.javatecsteps.iosteps;

import org.junit.Test;

import java.io.RandomAccessFile;

/**
 * Created by joker on 17-3-5.
 */
public class IOStepTest {
    @Test
    public void testStream(){
        ByteStreamStep byteStreamStep = new ByteStreamStep();
        byteStreamStep.testDataStream();
        byteStreamStep.testPushBackInputStream();
        byteStreamStep.testSequenceStream();
    }

    @Test
    public void testCharStream(){
        CharStreamStep charStreamStep = new CharStreamStep();
        charStreamStep.bufferedReadFile("/home/joker/bin/java_tec_test/test4.txt");
    }

    @Test
    public void testRandomAccessFile(){
        ByteStreamStep byteStreamStep = new ByteStreamStep();
        String filePath = "/home/joker/bin/java_tec_test/randomtest.txt";
        try{
            RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
            for (int i=0 ; i < 7; i++){
                rf.writeDouble(i * 1.414);
            }
            rf.writeUTF("the end of file");
            byteStreamStep.display(filePath);
            rf = new RandomAccessFile(filePath, "rw");
            rf.seek(5*8);
            rf.writeDouble(47.00);
            rf.close();
            byteStreamStep.display(filePath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void TestConvertSystemOutToPrinterWriter(){
        CharStreamStep charStreamStep = new CharStreamStep();
        charStreamStep.convertSystemOutToPrinterWriter();
//        charStreamStep.readFromStandardInput();
    }
    @Test
    public void testFileDeal(){
//        String zipPath = "/home/joker/bin/java_tec_test.zip";
//        List<String> strings = new ArrayList<String>();
//        File dir = new File("/home/joker/bin/java_tec_test");
//        if(dir.exists() && dir.isDirectory()){
//            File files[] = dir.listFiles();
//            if(files != null){
//                for(File one: files){
//                    strings.add(one.getAbsolutePath());
//                }
//            }
//        }
//
//        FileDealUtil.createZipFile(zipPath, strings, false);
        FileDealUtil.zipFolder("/home/joker/bin/java_tec_test");
    }
}
