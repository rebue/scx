package rebue.scx.doc;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static String concat(String... paths)
    {
        if (paths.length == 0) {
            return "";
        }
        boolean prevEndsWithSlash = paths[0].endsWith(File.separator);
        StringBuilder sb = new StringBuilder(paths[0]);
        for (int i = 1; i < paths.length; ++i) {
            String p = paths[i];
            if (prevEndsWithSlash) {
                if (p.startsWith(File.separator)) {
                    sb.append(p.substring(1));
                } else {
                    sb.append(p);
                }
            } else {
                if (p.startsWith(File.separator)) {
                    sb.append(p);
                } else {
                    sb.append(File.separator).append(p);
                }
            }
            prevEndsWithSlash = p.endsWith(File.separator);
        }
        return sb.toString();
    }

    public static String readFileStr(String fullPath) {
        String str = "";
        try (
                FileInputStream in = new FileInputStream(fullPath);
                ByteArrayOutputStream o = new ByteArrayOutputStream()) {
            int    r;
            byte[] buffer = new byte[1024];
            while ((r = in.read(buffer, 0, 1024)) != -1) {
                o.write(buffer, 0, r);
            }
            str = new String(o.toByteArray(), StandardCharsets.UTF_8);
        } catch (FileNotFoundException e) {
            System.out.println("------------------------找不到文件" + fullPath + "\n" + "开始查找bootstrap配置文件");
            return "找不到文件" + fullPath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
