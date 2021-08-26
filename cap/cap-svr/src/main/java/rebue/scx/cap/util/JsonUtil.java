package rebue.scx.cap.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rebue.scx.cap.mo.PointVO;

/**
 * 替换掉fastjson，自定义实现相关方法
 * note: 该实现不具有通用性，仅用于本项目。
 *
 *@author WongBin
 *@date 2021/1/8
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    public static List<PointVO> parseArray(final String text, final Class<PointVO> clazz) {
        if (text == null) {
            return null;
        } else {
            final String[] arr = text.replaceFirst("\\[","")
                    .replaceFirst("\\]","").split("\\}");
            final List<PointVO> ret = new ArrayList<>(arr.length);
            for (final String s : arr) {
                ret.add(parseObject(s,PointVO.class));
            }
            return ret;
        }
    }

    public static PointVO parseObject(final String text, final Class<PointVO> clazz) {
        if(text == null) {
            return null;
        }
        /*if(!clazz.isAssignableFrom(PointVO.class)) {
            throw new UnsupportedOperationException("不支持的输入类型:"
                    + clazz.getSimpleName());
        }*/
        try {
            final PointVO ret = clazz.newInstance();
            return ret.parse(text);
        }catch (final Exception ex){
            logger.error("json解析异常", ex);

        }
        return null;
    }

    public static String toJSONString(final Object object) {
        if(object == null) {
            return "{}";
        }
        if(object instanceof PointVO){
            final PointVO t = (PointVO)object;
            return t.toJsonString();
        }
        if(object instanceof List){
            final List<PointVO> list = (List<PointVO>)object;
            final StringBuilder buf = new StringBuilder("[");
            list.stream().forEach(t->{
                buf.append(t.toJsonString()).append(",");
            });
            return buf.deleteCharAt(buf.lastIndexOf(",")).append("]").toString();
        }
        if(object instanceof Map){
            return ((Map)object).entrySet().toString();
        }
        throw new UnsupportedOperationException("不支持的输入类型:"
                +object.getClass().getSimpleName());
    }
}
