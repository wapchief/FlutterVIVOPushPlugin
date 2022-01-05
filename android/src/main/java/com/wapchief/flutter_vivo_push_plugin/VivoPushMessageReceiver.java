package com.wapchief.flutter_vivo_push_plugin;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.model.UnvarnishedMessage;
import com.vivo.push.sdk.OpenClientPushMessageReceiver;

/**
 * @author wapchief
 * @date 2022/1/4
 */
public class VivoPushMessageReceiver extends OpenClientPushMessageReceiver {
    @Override
    public void onReceiveRegId(Context context, String s) {
        Log.d("vivo-tanliang", " onReceiveRegId= " + s);
    }

    @Override
    public void onTransmissionMessage(Context context, UnvarnishedMessage unvarnishedMessage) {
        super.onTransmissionMessage(context, unvarnishedMessage);
        Log.d("vivo-OpenClientPush", " onTransmissionMessage= " + unvarnishedMessage.getMessage());
    }

    @Override
    public void onNotificationMessageClicked(Context context, UPSNotificationMessage unvarnishedMessage) {
        super.onNotificationMessageClicked(context, unvarnishedMessage);
        Log.d("vivo-OpenClientPushMessageReceiverImpl", " onTransmissionMessage= " + unvarnishedMessage.toString());
    }
}
