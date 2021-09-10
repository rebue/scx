package com.maiyue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Main {

    private static final String COPY_BAT_PATH = "C:\\Users\\lq\\Desktop\\maiyue-workspace\\rebue\\server\\scx\\文档\\copy.bat";

    private static final String PATH_TO_SCX_SRC = "C:\\Users\\lq\\Desktop\\maiyue-workspace\\rebue\\server\\scx";

    private static final String OUTPUT_DIR = "C:\\Users\\lq\\Desktop\\services";

    private static final String VERSION = "1.2.4";

    private static final String NEW_LINE = "\r\n";

    private static final String[] SERVICES = new String[]{
            "gateway-server",
            "cap",
            "jwt",
            "oap",
            "orp",
            "oss",
            "rac",
            "rrl"
    };

    private static String outDir(String service)
    {
        if (service.equals("gateway-server")) {
            return service;
        }
        return service + "-svr";
    }

    public static void main(String[] args) throws IOException
    {
        StringBuilder result = new StringBuilder("chdir /d C:").append(NEW_LINE);
        for (String service : SERVICES) {
            File file = new File(OUTPUT_DIR + "\\" + outDir(service) + "\\config");
            if (!file.exists()) {
                file.mkdirs();
            }
            result.append("copy /y \"")
                    .append(PATH_TO_SCX_SRC)
                    .append("\\")
                    .append(pathToTarget(service))
                    .append("\\")
                    .append(jarName(service))
                    .append("\" ^")
                    .append(NEW_LINE);
            result.append("\t\"")
                    .append(OUTPUT_DIR)
                    .append("\\")
                    .append(outDir(service))
                    .append("\\")
                    .append(jarName(service))
                    .append("\"")
                    .append(NEW_LINE);

            result.append("copy /y \"")
                    .append(PATH_TO_SCX_SRC)
                    .append("\\")
                    .append(pathToTarget(service))
                    .append("\\classes\\config\\\" ^")
                    .append(NEW_LINE);
            result.append(" \"")
                    .append(OUTPUT_DIR)
                    .append("\\")
                    .append(outDir(service))
                    .append("\\config\\\"");
            result.append(NEW_LINE)
                    .append(NEW_LINE);
        }
        result.append("pause");
        try (FileOutputStream o = new FileOutputStream(COPY_BAT_PATH)) {
            o.write(result.toString().getBytes(StandardCharsets.UTF_8));
        }
    }

    private static String pathToTarget(String service)
    {
        if (service.equals("gateway-server")) {
            return "gateway-server\\target";
        }
        return service + "\\" + service + "-svr\\target";
    }

    private static String jarName(String service)
    {
        if (service.equals("gateway-server")) {
            return "gateway-server-" + VERSION + ".jar";
        }
        return service + "-svr-" + VERSION + ".jar";
    }

}
