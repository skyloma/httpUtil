package lomasky.ma.httpUtils;

import android.support.annotation.UiThread;

/**
 * @author loma
 * @Description:
 * @date: $(DATE)
 * @version: V2.0
 */
public interface ICallBack {
    @UiThread
    void  err(String e);
    @UiThread
    void  ok(ResponseBean response);
}
