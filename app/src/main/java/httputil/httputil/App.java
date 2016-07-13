package httputil.httputil;

import android.app.Application;
import android.os.Build;

import com.socks.library.KLog;

import httputil.httputil.BuildConfig;
import lomasky.ma.httpUtils.HttpUtil;

/**
 * @author loma
 * @Description:
 * @date: 2016/7/13 0013
 * @version: V2.0
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(BuildConfig.LOG_DEBUG, "zjt");
        HttpUtil.initServerConfig(this);
    }
}
