package com.yitianyike.myssm.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author xujinbo
 */
public class FileOptUtil {

    private static final Logger LOGGER = Logger.getLogger(FileOptUtil.class);

    public static void copyFile(File source, File target) throws IOException {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fi = new FileInputStream(source);
            fo = new FileOutputStream(target);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
        } finally {
            fi.close();
            in.close();
            fo.close();
            out.close();
        }
    }

    public static void copyFile(String source, String target) throws IOException {
        copyFile(new File(source), new File(target));
    }

    public static boolean appendContent(String file, String conent) throws IOException {
        BufferedWriter out = null;
        boolean append = true;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(conent);
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
            append = false;
        } finally {
            out.close();
        }
        return append;
    }

    public static List<String> getFileContent(String filePath) {
        List<String> imsis = new ArrayList<String>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(
                        file)));
                String lineTxt = null;
                while ((lineTxt = br.readLine()) != null) {
                    for (String imsi : lineTxt.split(",")) {
                        if (!imsis.contains(imsi)) {
                            imsis.add(imsi);
                        }
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            LOGGER.error("读取文件内容出错:" + e.getMessage());
        }
        return imsis;
    }

    public static void main(String[] args) throws IOException {
        copyFile("F:\\nginx-1.7.5\\html\\edit\\images\\1412736533203-62-3.jpeg",
                "F:\\nginx-1.7.5\\html\\edit\\images\\xx1212xxxxxx.jpeg");
    }
}
