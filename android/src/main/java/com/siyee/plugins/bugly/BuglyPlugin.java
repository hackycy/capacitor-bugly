package com.siyee.plugins.bugly;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;

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

        String appId = this.getConfig().getString("appId");
        Boolean debug = this.getConfig().getBoolean("debug", false);
        String deviceId = this.prefs.getString("buglyAppUUID", UUID.randomUUID().toString());
        this.editor.putString("buglyAppUUID", deviceId);

        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getContext());
        strategy.setDeviceID(deviceId);
        strategy.setDeviceModel(Build.MODEL);

        // init
        CrashReport.initCrashReport(getActivity().getApplicationContext(), appId, debug, strategy);
    }

    @PluginMethod
    public void echo(PluginCall call) {
        CrashReport.testJavaCrash();
        call.resolve();
    }
}
