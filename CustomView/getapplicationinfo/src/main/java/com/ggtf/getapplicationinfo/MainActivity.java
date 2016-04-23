package com.ggtf.getapplicationinfo;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static final int FILTER_ALL_APP = 0; // 所有应用程序
    public static final int FILTER_SYSTEM_APP = 1; // 系统程序
    public static final int FILTER_THIRD_APP = 2; // 第三方应用程序
    public static final int FILTER_SDCARD_APP = 3; // 安装在SDCard的应用程序
    private int filter = FILTER_ALL_APP;
    private List<ApplicationInfo> applicationInfo;
    private PackageManager packageManager;
    private List<String> appInfo;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationInfo = new LinkedList<>();
        listView = (ListView) findViewById(R.id.list_view);
        appInfo = new LinkedList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, appInfo);
        listView.setAdapter(adapter);
//        init();
    }

    private void init() {
        if (getIntent() != null) {
            filter = getIntent().getIntExtra("filter", 0);
        }

    }

    private void queryFilterAppInfo(int filter) {
        packageManager = getPackageManager();
        List<ApplicationInfo> applicationInfoList = packageManager.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(applicationInfoList, new ApplicationInfo.DisplayNameComparator(packageManager));
        switch (filter) {
            case FILTER_ALL_APP:
                applicationInfo.clear();
                for (ApplicationInfo app : applicationInfoList) {
                    applicationInfo.add(app);
                }
                break;
            case FILTER_SYSTEM_APP:
                /**
                 * 系统应用程序
                 */
                applicationInfo.clear();
                for (ApplicationInfo app : applicationInfoList) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        applicationInfo.add(app);
                    }
                }
                break;
            case FILTER_THIRD_APP:
                /**
                 * 第三方应用程序
                 */
                for (ApplicationInfo app : applicationInfoList) {
                    /**
                     * 非系统程序
                     */
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
                        applicationInfo.add(app);
                    }else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) !=0){
                        /**
                         * 本来是系统程序,被用户手动更新后,该系统也成为第三方应用程序
                         */
                        applicationInfo.add(app);
                    }
                }
                break;
            case FILTER_SDCARD_APP:
                /**
                 * SD卡内的应用程序
                 */
                for (ApplicationInfo app : applicationInfoList) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        applicationInfo.add(app);
                    }
                }
                break;
        }
        if (applicationInfo.size()>0){
            for (ApplicationInfo app :applicationInfo){
                appInfo.add(app.packageName);
            }
        }
    }

    public void btnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.all_app:
                appInfo.clear();
                queryFilterAppInfo(FILTER_ALL_APP);
                adapter.notifyDataSetChanged();
                break;
            case R.id.system_app:
                appInfo.clear();
                queryFilterAppInfo(FILTER_ALL_APP);
                adapter.notifyDataSetChanged();
                break;
            case R.id.thrid_app:
                appInfo.clear();
                queryFilterAppInfo(FILTER_ALL_APP);
                adapter.notifyDataSetChanged();
                break;
            case R.id.sd_card_app:
                appInfo.clear();
                queryFilterAppInfo(FILTER_ALL_APP);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
