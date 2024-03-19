package org.jay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IOUtils {

    // 读取文件内容
    public static String getContext(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, len));
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
