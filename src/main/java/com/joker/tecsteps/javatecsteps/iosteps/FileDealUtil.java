package com.joker.tecsteps.javatecsteps.iosteps;

import java.io.*;
import java.nio.channels.FileLock;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by joker on 17-3-5.
 */
public class FileDealUtil {
    /**
     * 复制文件
     * @param fromFilePath
     * @param toFilePath
     * @param deleteExist
     * @return
     */
    public static boolean copyFile(String fromFilePath, String toFilePath, boolean deleteExist) {
        File dstFile = new File(toFilePath);
        if (dstFile.exists()) {
            if (deleteExist) {
                dstFile.delete();
            } else {
                return true;
            }
        }

        try {
            File fromFile = new File(fromFilePath);
            if (!fromFile.exists()) {
                return false;
            }
            if (!fromFile.canRead()) {
                return false;
            }
            FileInputStream inputStream = new FileInputStream(fromFile);
            FileOutputStream outputStream = new FileOutputStream(dstFile);
            byte[] bytes = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, length);
            }
            inputStream.close();
            outputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从一个 流中 写入文件
     * @param dstFilePath
     * @param inputStream
     * @return
     */
    public static boolean writeToFile(String dstFilePath, InputStream inputStream) {
        File dstFile = new File(dstFilePath);
        if (dstFile.exists() || inputStream == null) {
            return false;
        }
        FileOutputStream fileWriter = null;
        BufferedOutputStream writer = null;
        BufferedInputStream reader = null;
        try {
            fileWriter = new FileOutputStream(dstFile);
            writer = new BufferedOutputStream(fileWriter);
            reader = new BufferedInputStream(inputStream);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, len);
                writer.flush();
            }
            reader.close();
            writer.close();
            fileWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 删除一个目录
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] childrens = dir.list();
            for (String child : childrens) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * 将多个文件打包为一个 zip
     * @param zipSavePath
     * @param fileNames
     * @param delFile
     * @return
     */
    public static boolean createZipFile(String zipSavePath, List<String> fileNames, boolean delFile) {
        if (null == fileNames || fileNames.size() < 1) {
            return false;
        }

        boolean result = false;
        ZipOutputStream zipWriter = null;
        FileInputStream fileReader = null;
        try {
            zipWriter = new ZipOutputStream(new FileOutputStream(zipSavePath));
            for (String fileName : fileNames) {
                File file = new File(fileName);
                if (file.exists() && file.length() > 0) {
                    fileReader = new FileInputStream(file);
                    // 每一个要加入压缩档案的文件都必须调用putNextEntry
                    zipWriter.putNextEntry(new ZipEntry(file.getName()));
                    byte[] bytes = new byte[1024];

                    while (fileReader.read(bytes) > 0) {
                        zipWriter.write(bytes);
                    }
                }
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (result && delFile) {
                for (String value : fileNames) {
                    File file = new File(value);
                    file.delete();
                }
            }
            try {
                if (zipWriter != null) {
                    zipWriter.closeEntry();
                    zipWriter.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 压缩某个目录
     * @param folder
     */
    public static void zipFolder(String folder) {
        File file = new File(folder);
        try {
            if(file.exists() && file.canRead()){
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(folder+".zip"));
                // 找到最后一个 /的诶之 以便取出当前的文件名或者目录名
                zip(file.getPath(), file.getPath().lastIndexOf("//"), out);
                out.closeEntry();
                out.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void zip(String path, int baseindex, ZipOutputStream out) throws IOException {
        //要压缩的目录或文件
        File file = new File(path);
        File[] files;
        if (file.isDirectory()) {//若是目录，则列出所有的子目录和文件
            files = file.listFiles();
        } else {//若为文件，则files数组只含有一个文件
            files = new File[1];
            files[0] = file;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                //去掉压缩根目录以上的路径串，一面ZIP文件中含有压缩根目录父目录的层次结构
                String pathname = f.getPath().substring(baseindex + 1);
                //空目录也要新建一个项
                out.putNextEntry(new ZipEntry(pathname + "/"));
                //递归
                zip(f.getPath(), baseindex, out);
            } else {
                //去掉压缩根目录以上的路径串，一面ZIP文件中含有压缩根目录父目录的层次结构
                String pathname = f.getPath().substring(baseindex + 1);
                //新建项为一个文件
                out.putNextEntry(new ZipEntry(pathname));
                //读文件
                BufferedInputStream in = new BufferedInputStream(
                        new FileInputStream(f));
                int c;
                while ((c = in.read()) != -1)
                    out.write(c);
                in.close();
            }
        }
    }

    /**
     * 锁定文件
     * @param filePath
     * @return
     */
    public static FileLock lockFile(String filePath){
        File file = new File(filePath);
        try{
            RandomAccessFile rf = new RandomAccessFile(file, "rw");
            FileLock fileLock = rf.getChannel().lock();
            if(fileLock.isValid()){
                return fileLock;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return null;
    }

}
