import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:tiktok_clone/authentication/authentication_controller.dart';
import 'package:tiktok_clone/authentication/login_screen.dart';

void main() async
{
  WidgetsFlutterBinding.ensureInitialized();

  Firebase.initializeApp().then((value)
  {
    Get.put(AuthenticationController());
  });

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'TikTok',
      theme: ThemeData.dark().copyWith(
        scaffoldBackgroundColor:Colors.white,
      ),
      home: LoginScreen(),
    );
  }
}

