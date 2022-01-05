import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_vivo_push_plugin/flutter_vivo_push_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    WidgetsBinding.instance?.addPostFrameCallback((_) {
      FlutterVivoPushPlugin.initSDK();
    });
  }

  // Platform messages are asynchronous, so we initialize in an async method.

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: [
              TextButton(
                  onPressed: () {
                    FlutterVivoPushPlugin.openPush();
                  },
                  child: const Text('打开推送')),
              TextButton(
                  onPressed: () {
                    FlutterVivoPushPlugin.offPush();
                  },
                  child: const Text('关闭推送'))
            ],
          ),
        ),
      ),
    );
  }
}
