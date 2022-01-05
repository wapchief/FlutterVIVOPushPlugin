package com.wapchief.flutter_vivo_push_plugin;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.util.VivoPushException;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * FlutterVivoPushPlugin
 */
public class FlutterVivoPushPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private Application mApplication;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        mApplication = (Application) flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_vivo_push_plugin");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "initSDK":
                Log.d("vivo-inSDK", "");
                //初始化sdk
                try {
                    PushClient.getInstance(mApplication).initialize();

                } catch (VivoPushException e) {
                    Log.d("vivo-init-error", e.toString());
                }
                break;
            case "openPush":
                //打开推送
                Log.d("vivo-openPush", "");
                PushClient.getInstance(mApplication).turnOnPush(state -> {
                    Log.d("vivo-openPush-state:", state + "");
                    if (state == 0) {
                        Log.d("vivo-pushId:", PushClient.getInstance(mApplication).getRegId());
                    }
                });
                break;
            case "offPush":
                //关闭推送
                PushClient.getInstance(mApplication).turnOffPush(new IPushActionListener() {
                    @Override
                    public void onStateChanged(int state) {

                    }
                });
                break;
            default:
                Log.d("vivo-FlutterVivoPushPluginr", call.method.toString());
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
