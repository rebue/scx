package com.github.rebue.third.party.demo.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.util.ResourceUtils;

public class ResourcesWrapper {

    public static InputStream getInputStream(String fileName, Class<?> clazz) throws IOException
    {
        InputStream in;
        try {
            in = clazz.getClassLoader().getResourceAsStream(fileName);
            if (in == null) {
                throw new RuntimeException();
            }
            return in;
        } catch (Exception ignore) {
        }
        try {
            in = clazz.getResourceAsStream(fileName);
            if (in == null) {
                throw new RuntimeException();
            }
            return in;
        } catch (Exception ignore) {
            return new FileInputStream(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName));
        }
    }

}
