package com.ustc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author 田宝宁
 * @date 2022/04/10
 */
public class FileZipUtils {
    /**
     * 压缩文件夹
     * @param sourcePath    源文件夹路径
     * @param zipPath       压缩文件保存路径
     */
    public static void fileToZip(String sourcePath, String zipPath) {
        try {
            File zipFile = new File(zipPath);
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            recursionZip(sourcePath, "", zipOut);
            zipOut.close();
            delFile(sourcePath);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void delFile(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                f.delete();
            } else {
                recursionDel(f);
            }
        }
        file.delete();
    }

    private static void recursionDel(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isFile()) {
                f.delete();
            } else {
                recursionDel(f);
            }
        }
        file.delete();
    }

    /**
     * 递归压缩文件
     * @param filePath      文件路径
     * @param relativePath  相对路径
     * @param zipOut        输出流
     */
    public static void recursionZip(String filePath, String relativePath, ZipOutputStream zipOut) throws Exception {
        File srcFile = new File(filePath);
        File[] files = srcFile.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                zipFile(file, relativePath, zipOut);
            } else {
                recursionZip(filePath + "/" + file.getName(),
                        relativePath + "/" + file.getName(), zipOut);
            }
        }
    }

    /**
     * 压缩执行函数
     * @param file          待压缩文件
     * @param relativePath  相对路径
     * @param zipOut        输出流
     */
    public static void zipFile(File file, String relativePath, ZipOutputStream zipOut) throws Exception {
        InputStream input = new FileInputStream(file);
        zipOut.putNextEntry(new ZipEntry(relativePath + "/" + file.getName()));
        byte[] buffer = new byte[1024];
        int readLen;
        while ((readLen = input.read(buffer)) != -1) {
            zipOut.write(buffer, 0, readLen);
        }
        input.close();
        zipOut.closeEntry();
    }
}
