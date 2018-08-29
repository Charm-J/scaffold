package com.dj.scaffold.util;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.List;
import java.util.Random;

public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        FileOutputStream fos = null;
        try {
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            fos = new FileOutputStream(filePath + fileName);
            fos.write(file);
            fos.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     *  远程文件下载
     */
    public static String download(String urlString, String filename, String savePath) throws Exception {
        String finalFilePath = "";
        InputStream is = null;
        OutputStream os = null;
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5 * 1000);
            // 输入流
            is = con.getInputStream();

            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            finalFilePath = sf.getPath() + File.separator + filename;
            os = new FileOutputStream(finalFilePath);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                finalFilePath = "";
                e.printStackTrace();
            }
        }
        return finalFilePath;
    }

    /**
     * 生成文件 md5
     */
    public static String getFileMd5(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }


    public static String getRandomFromList(List<String> list) {
        if (list.isEmpty()) {
            return "";
        }
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }

}
