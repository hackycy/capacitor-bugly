package com.siyee.plugins.bugly;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.plugin.WebView;
import com.tencent.bugly.crashreport.CrashReport;
import java.util.UUID;

@CapacitorPlugin(name = "Bugly")
public class BuglyPlugin extends Plugin {

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;

    @Override
    public void load() {
        super.load();
        this.prefs = this.getContext().getSharedPreferences(WebView.WEBVIEW_PREFS_NAME, Activity.MODE_PRIVATE);
        this.editor = this.prefs.edit();

        boolean autoInit = this.getConfig().getBoolean("autoInit", true);

        if (autoInit) {
            this.initBugly();
        }
    }

    @PluginMethod
    public void initCrashReport(PluginCall call) {
        this.initBugly();
        call.resolve();
    }

    @PluginMethod
    public void setUserValue(PluginCall call) {
        String key = call.getString("key");
        String value = call.getString("value");

        if (TextUtils.isEmpty(key) || TextUtils.isEmpty(value)) {
            call.reject("User Data is Empty");
            return;
        }

        CrashReport.putUserData(getContext(), key, value);
        call.resolve();
    }

    @PluginMethod
    public void setUserSceneTag(PluginCall call) {
        Integer tag = call.getInt("tag");
        if (tag == null) {
            call.reject("Tag is empty");
            return;
        }

        CrashReport.setUserSceneTag(getContext(), tag);
    }

    private void initBugly() {
        String androidAppId = this.getConfig().getString("androidAppId");
        boolean debug = this.getConfig().getBoolean("debug", false);
        boolean enableCatchAnrTrace = this.getConfig().getBoolean("enableCatchAnrTrace", false);
        boolean enableRecordAnrMainStack = this.getConfig().getBoolean("enableRecordAnrMainStack", true);

        String deviceId = this.prefs.getString("buglyAppUUID", UUID.randomUUID().toString());
        this.editor.putString("buglyAppUUID", deviceId);

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getContext());
        strategy.setDeviceID(deviceId);
        strategy.setDeviceModel(Build.MODEL);
        strategy.setEnableCatchAnrTrace(enableCatchAnrTrace);
        strategy.setEnableRecordAnrMainStack(enableRecordAnrMainStack);

        // init
        CrashReport.initCrashReport(getActivity().getApplicationContext(), androidAppId, debug, strategy);
    }
}
