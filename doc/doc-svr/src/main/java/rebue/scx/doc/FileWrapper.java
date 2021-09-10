package rebue.scx.doc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 读取文件
 * 
 * @author yuanman
 *
 */
public class FileWrapper {

    public static String readFileStrNoExcept(String fullPath) {
        try {
            return readFileStr(fullPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String readFileStr(String fullPath) throws IOException {
        try (
                FileInputStream in = new FileInputStream(fullPath);
                ByteArrayOutputStream o = new ByteArrayOutputStream()) {
            int    r;
            byte[] buffer = new byte[1024];
            while ((r = in.read(buffer, 0, 1024)) != -1) {
                o.write(buffer, 0, r);
            }
            return new String(o.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    public static void writeToFile(String content, String outputPath) throws IOException {
        try (FileOutputStream o = new FileOutputStream(outputPath)) {
            o.write(content.getBytes(StandardCharsets.UTF_8));
        }
    }

}
