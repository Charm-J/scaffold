package com.dj.scaffold.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    public void compress(String filePath) throws Exception{

        File file = new File(filePath);
        ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
        File fout = null;
        String parent = file.getParent();
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
            fout = new File(parent, entry.getName());
            if (!fout.exists()) {
                (new File(fout.getParent())).mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fout));
            int b = -1;
            byte[] buffer = new byte[1024];
            while ((b = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, b);
            }
            bos.close();
        }
        zis.close();
    }
}
