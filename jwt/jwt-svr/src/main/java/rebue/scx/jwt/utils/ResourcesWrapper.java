package rebue.scx.jwt.utils;

import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.InputStream;

public class ResourcesWrapper {

    public static InputStream getInputStream(String fileName) throws Exception
    {
        try {
            return ResourcesWrapper.class.getClassLoader().getResourceAsStream(fileName);
        } catch (Exception e) {
            return new FileInputStream(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + fileName));
        }
    }

}
