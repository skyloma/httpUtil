package httputil.httputil;

import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import lomasky.ma.httpUtils.HttpUtil;
import lomasky.ma.httpUtils.ICallBack;
import lomasky.ma.httpUtils.ResponseBean;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

//                progress=new ProgressDialog(MainActivity.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
//                progress.setMessage("Downloading Music");
////                progress.setProgressStyle(android.R.style.Animation_Dialog);
////                progress.setCancelable(false);
////                progress.setIndeterminate(true);
////                progress.setProgress(0);
//                progress.show();
                Map<String, Object> params = new HashMap<>();
                params.put("UserName", "123");
                params.put("Password", "123456");

                HttpUtil.post(MainActivity.this, "/login", params, "正在登录", new ICallBack() {
                    @Override
                    public void err(String e) {
                        KLog.e(e);
                    }

                    @Override
                    public void ok(ResponseBean response) {
                        KLog.json(response.content);

                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
