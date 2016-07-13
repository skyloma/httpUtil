package lomasky.ma.httpUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.TextView;

/**
 * @author Eicky
 * @Description: 请求数据加载对话框
 * @date: 2016/6/2 14:59
 * @version: V1.0
 */
public class ProgressDialogUtil {
    private static ProgressDialog progressDialog;//进度框

    /**
     * @param context    上下文
     * @param cancel 显示的提示信息
     */
    public static void showProgressDialog(Context context, boolean cancel) {
        Activity activity = (Activity) context;
        if (!activity.isFinishing()) {
            if (progressDialog == null) {
                progressDialog =    new ProgressDialog(context,android.R.style.Theme_DeviceDefault_Light_Dialog);
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
//                Window win = progressDialog.getWindow();
//                win.setContentView(R.layout.post_progress_bar);
            }
            progressDialog.setCanceledOnTouchOutside(cancel);
            progressDialog.show();
        }
    }

    /**
     * @param context    上下文
     * @param cancel 显示的提示信息
     */
    public static void showProgressDialog(Context context, boolean cancel, String msg) {
        Activity activity = (Activity) context;
        if (!activity.isFinishing()) {
            if (progressDialog == null) {
                progressDialog =    new ProgressDialog(context,android.R.style.Theme_DeviceDefault_Light_Dialog);
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode,
                                         KeyEvent event) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                dialog.dismiss();
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });


                progressDialog.setCanceledOnTouchOutside(cancel);
            }
            progressDialog.setMessage(msg);
            progressDialog.show();
        }
    }

    /**
     * 关闭进度对话框
     */
    public static void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
