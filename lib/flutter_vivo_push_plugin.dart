import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';
export 'package:flutter_vivo_push_plugin/flutter_vivo_push_plugin.dart';
class FlutterVivoPushPlugin {
  static const MethodChannel _channel = MethodChannel('flutter_vivo_push_plugin');

  static initSDK() {
    // if (Platform.isIOS) {
    //   return;
    // }
    _channel.invokeMethod('initSDK');
  }

  static openPush() {
    // if (Platform.isIOS) {
    //   return;
    // }
    _channel.invokeMethod('openPush');
  }

  static offPush() {
    if (Platform.isIOS) {
      return;
    }
    _channel.invokeMethod('offPush');
  }
}
