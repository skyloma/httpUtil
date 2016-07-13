package lomasky.ma.httpUtils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.socks.library.KLog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author loma
 * @Description: 基于okHttp的网络请求工具
 * @date: 2016/6/1 11:37
 * @version: V1.0
 */
public class HttpUtil {
    //    /**
//     * 赛帆演示版本
//     */
    public static String URL_IP;
    public static String URL_ROOT;

    //    public static String URL_IP = "http://192.168.1.143:1314";
//    public static String URL_ROOT = "http://192.168.1.143:1314/api/Customer";
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    public static void initServerConfig(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            URL_IP = applicationInfo.metaData.getString("URL_IP");
            URL_ROOT = applicationInfo.metaData.getString("URL_ROOT");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void post(final Activity context, final String api, Map<String, Object> map, String tip, final ICallBack callBack) {
       if (TextUtils.isEmpty(URL_IP)||TextUtils.isEmpty(URL_ROOT)){
           initServerConfig(context);
       }
        KLog.d("参数" + api);
        KLog.json(map.toString());
        ProgressDialogUtil.showProgressDialog(context, false, tip);
        //builder一个OkHttpClient对象
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(120, TimeUnit.SECONDS);
        OkHttpClient okClient = builder.build();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        final String json = JSON.toJSONString(map);
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(  URL_ROOT + api)
                .post(requestBody)
                .build();
        //发送请求获取响应
        okClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                KLog.e(e);
                e.printStackTrace();
                ProgressDialogUtil.closeProgressDialog();
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callBack.err(e.getLocalizedMessage());
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ProgressDialogUtil.closeProgressDialog();
                final String string = response.body().string();
                KLog.d("返回数据" + api);
                KLog.json(string);
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int code = jsonObject.getInt("Code");
                            String content = jsonObject.getString("Content");
                            ResponseBean responseBean = new ResponseBean(api, code, content);
                            callBack.ok(responseBean);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
        });


    }

    public static void post(final Activity context, final String api, Map<String, Object> map, final ICallBack callBack) {
        if (TextUtils.isEmpty(URL_IP)||TextUtils.isEmpty(URL_ROOT)){
            initServerConfig(context);
        }
        KLog.d("参数" + api);
        KLog.json(map.toString());
        //builder一个OkHttpClient对象
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(120, TimeUnit.SECONDS);
        OkHttpClient okClient = builder.build();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        final String json = JSON.toJSONString(map);


        RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, json);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(URL_ROOT + api)
                .post(requestBody)
                .build();
        //发送请求获取响应
        okClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                KLog.e(e.toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                KLog.d("返回数据" + api);
                KLog.json(string);
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            JSONObject jsonObject = new JSONObject(string);
                            int code = jsonObject.getInt("Code");
                            String content = jsonObject.getString("Content");
                            ResponseBean responseBean = new ResponseBean(api, code, content);
                            callBack.ok(responseBean);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
        });


    }


}
