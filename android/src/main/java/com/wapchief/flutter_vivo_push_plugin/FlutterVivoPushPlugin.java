package com.wapchief.flutter_vivo_push_plugin;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.ups.TokenResult;
import com.vivo.push.ups.UPSRegisterCallback;
import com.vivo.push.ups.VUpsManager;
import com.vivo.push.util.VivoPushException;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * FlutterVivoPushPlugin
 */
public class FlutterVivoPushPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private static Application mApplication;
    private static Activity mActivity;

    private static String TAG = "FlutterVivoPushPlugin";

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        mApplication = (Application) flutterPluginBinding.getApplicationContext();
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_vivo_push_plugin");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (!Build.MANUFACTURER.toLowerCase().contains("vivo")) {
            Log.d(TAG, "Not vivo push mobile devices !!!");
            return;
        }
        switch (call.method) {
            case "initSDK":
                try {
                    Log.d(TAG, "-----vivo-openPush-mApplication:" + mApplication.getBaseContext().toString());
                    //初始化sdk
                    PushClient.getInstance(mApplication).initialize();
                    //打开推送
                    VUpsManager.getInstance().turnOnPush(mApplication, codeResult -> {
                        //code:101系统不支持
                        if (codeResult.getReturnCode() == 101) {
                            Log.e(TAG, "-----vivo-openPush-error:101系统不支持");
                        } else {
                            Log.v(TAG,
                                    "-----vivo-openPush-codeResult:" + codeResult.getReturnCode());
                        }

                    });
                } catch (Exception e) {
                    Log.e(TAG, e.toString());
                }
                break;
            case "getRegId":
                result.success(PushClient.getInstance(mApplication).getRegId());
                break;
            case "openPush":
                //打开推送
                VUpsManager.getInstance().turnOnPush(mApplication, codeResult -> {
                    //code:101系统不支持
                    Log.v(TAG, "-----vivo-openPush-codeResult:" + codeResult.getReturnCode());

                });
                break;
            case "offPush":
                //关闭推送
                PushClient.getInstance(mApplication).turnOffPush(new IPushActionListener() {
                    @Override
                    public void onStateChanged(int state) {
                        Log.v(TAG, "-----vivo-closePush-state:" + state + "");
                    }
                });
                break;
            case "registerToken":
                String appId = call.argument("appId");
                String appKey = call.argument("appKey");
                String secret = call.argument("secret");
                VUpsManager.getInstance().registerToken(mApplication, appId, appKey,
                        secret, tokenResult -> {
                            if (tokenResult.getReturnCode() == 0) {
                                Log.v(TAG, "registerToken 注册成功 regID = " + tokenResult.getToken());
                            } else {
                                Log.v(TAG, "registerToken 注册失败");
                            }
                        });
                break;
            default:
                Log.d(TAG, "------method:" + call.method.toString());
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        mActivity = binding.getActivity();
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {

    }

    @Override
    public void onDetachedFromActivity() {

    }
}
