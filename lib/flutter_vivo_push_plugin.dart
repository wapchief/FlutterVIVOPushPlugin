
import 'dart:async';

import 'package:flutter/services.dart';

class FlutterVivoPushPlugin {
  static const MethodChannel _channel = MethodChannel('flutter_vivo_push_plugin');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
