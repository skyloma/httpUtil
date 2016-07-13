package lomasky.ma.httpUtils;



import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Eicky
 * Date: 2015-12-08
 * Content:
 */
public class JsonUtil {

    public static <T> T getObject(String jsonStr, Class<T> cls) {
        return JSON.parseObject(jsonStr,cls);
    }

    public static <T> List<T> getList(String json, Class<T> cls) {
        return JSON.parseArray(json,cls);
    }
    public static String toJson(Object object) {
        return JSON.toJSONString(object);
    }


}
