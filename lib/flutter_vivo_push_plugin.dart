import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';
export 'package:flutter_vivo_push_plugin/flutter_vivo_push_plugin.dart';

class FlutterVivoPushPlugin {
  static const MethodChannel _channel = MethodChannel('flutter_vivo_push_plugin');

  static initSDK() {
    _channel.invokeMethod('initSDK');
  }

  static openPush() {
    _channel.invokeMethod('openPush');
  }

  static offPush() {
    _channel.invokeMethod('offPush');
  }

  static Future<String> getRegId() async {
    return await _channel.invokeMethod('getRegId');
  }

  static registerOPPOToken(
      {required String appId, required String appKey, required String secret}) {
    _channel.invokeMethod('registerToken', {
      'appId': appId,
      'appKey': appKey,
      'secret': secret,
    });
  }
}
