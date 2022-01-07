# flutter_vivo_push_plugin

A new Flutter project.

## Getting Started

SDK版本 484

使用：

 /android/app/src/man/AndroidManifest.xml 标签下 <application> 添加

```
        <meta-data
            android:name="com.vivo.push.app_id"
            android:value="替换成自己的" />
        <meta-data
            android:name="push_kit_auto_init_enabled"
            android:value="true" />

        <meta-data
            android:name="com.vivo.push.api_key"
            android:value="替换成自己的" />
```


```
FlutterVivoPushPlugin.initSDK();
FlutterVivoPushPlugin.openPush();   
FlutterVivoPushPlugin.registerOPPOToken(
        appId: '',
        appKey: '',
        secret: '');

```



