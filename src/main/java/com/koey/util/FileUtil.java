package com.koey.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class FileUtil {


    public static void main(String[] args) throws IOException {
        // compareTwoFile("D:\\WorkSpace_FX\\MyFX1.0\\src\\main\\java\\com\\koey\\view\\player\\LuoTuo.java", "D:\\WorkSpace_FX\\MyFX1.0\\src\\main\\java\\com\\koey\\view\\player\\LuoTuo2.java");
        String str = "com.koey.util";
        String[] ss = str.split("\\.");
        System.out.println(ss[0]);
    }


    /**
     * 对比两个文件的差异，并且输出新文件与旧文件的差异
     *
     * @param oldFile 旧的文件
     * @param newFile 当前新文件
     * @throws IOException 异常
     */
    public static void compareTwoFile(String oldFile, String newFile) throws IOException {

        File fileOld = new File(oldFile);
        File fileNew = new File(newFile);

        FileInputStream inputStream1 = new FileInputStream(fileOld);
        int size1 = inputStream1.available();
        byte[] buffer1 = new byte[size1];
        inputStream1.read(buffer1);
        inputStream1.close();
        String fileOldStr = new String(buffer1, "UTF-8");

        FileInputStream inputStream2 = new FileInputStream(fileNew);
        int size2 = inputStream2.available();
        byte[] buffer2 = new byte[size2];
        inputStream2.read(buffer2);
        inputStream2.close();
        String fileNewStr = new String(buffer2, "UTF-8");

        if (fileNewStr.equals(fileOldStr)){
            System.out.println("两个文件完全相同！");
            return;
        }

        String[] oldSplit = fileOldStr.split("\n");
        String[] newSplit = fileNewStr.split("\n");


        if (newSplit != null && oldSplit != null) {
            //旧文件
            System.out.println("================旧文件===================");
            for (int i = 0; i < oldSplit.length; i++) {
                if (!oldSplit[i].equals(newSplit[i])) {
                    System.out.println(i + ":" + oldSplit[i]);
                }
            }

            //新文件
            System.out.println("================新文件===================");
            for (int i = 0; i < newSplit.length; i++) {
                if (i < oldSplit.length) {
                    if (!newSplit[i].equals(oldSplit[i])) {
                        System.out.println(i + ":" + newSplit[i]);
                    }
                } else {
                    System.out.println(i + ":" + newSplit[i]);
                }
            }
        }
    }

}
