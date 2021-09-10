package rebue.scx.doc;

import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YmlProperties {
    /**
     * 根据fields读取yml文件获取值
     * 
     * @param pathToYmlDir 文件路径
     * @param profile      dev或者prod
     * @param fields       读取的Key
     * 
     * @return
     */
    public static Map<String, String> getProperties(String pathToYmlDir, String profile, String... fields) {
        Map<String, String> result = new LinkedHashMap<>();
        Yaml                yaml;
        String              ymlStr;
        try {
            // yaml = new Yaml();
            // ymlStr = FileWrapper.readFileStr(pathToYmlDir + "\\application.yml");
            // Object ymlProperties = yaml.load(ymlStr);

            yaml   = new Yaml();
            ymlStr = FileWrapper.readFileStr(pathToYmlDir + "bootstrap-" + profile + ".yml");
            Object profileProperties = yaml.load(ymlStr);

            for (String field : fields) {
                String valueFromYml = (String) getValueFromYml(profileProperties, field, String.class);
                if (valueFromYml == null) {
                    // valueFromYml = (String) getValueFromYml(ymlProperties, field, String.class);
                    if (valueFromYml == null) {
                        throw new RuntimeException();
                    }
                }
                result.put(field, valueFromYml);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    private static Object getValueFromYml(Object ymlProperties, String springValue, Class<?> clazz) {
        // springValue = springValue.substring(2, springValue.length() - 1);
        Object raw = ((LinkedHashMap) ymlProperties).get(springValue);
        if (raw != null) {
            return valueToString(clazz, raw);
        }
        String[] keys = springValue.split("\\.");
        Object   m    = ymlProperties;
        Object   value;
        for (String key : keys) {
            value = ((LinkedHashMap) m).get(key);
            if (value == null) {
                return null;
            }
            if (value instanceof LinkedHashMap) {
                m = value;
                continue;
            }
            return valueToString(clazz, value);
        }
        return null;
    }

    private static Object valueToString(Class<?> clazz, Object value) {
        if (clazz == String.class) {
            if (value instanceof String) {
                return value;
            }
            if (value instanceof Integer) {
                return String.valueOf(value);
            }
        }
        if (clazz == Integer.class) {
            if (value instanceof Integer) {
                return value;
            }
            if (value instanceof String) {
                return Integer.parseInt((String) value);
            }
        }
        throw new RuntimeException();
    }

}
