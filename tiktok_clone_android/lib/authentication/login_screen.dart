import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:simple_circular_progress_bar/simple_circular_progress_bar.dart';
import 'package:tiktok_clone/authentication/registration_screen.dart';
import 'package:tiktok_clone/widgets/input_text_widget.dart';

class LoginScreen extends StatefulWidget {
  const LoginScreen({super.key});

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  TextEditingController emailTextEditingController = TextEditingController();
  TextEditingController passwordTextEditingController = TextEditingController();
  bool showProgressBar = false;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
          child: Center(
            child: Column(
              children: [
                const SizedBox(
                  height: 50,
                ),

                Image.asset(
                  "images/TikTok-Logo.png",
                  width: 150,
                ),
                const SizedBox(
                  height: 50,
                ),

                Text(
                  "Đăng nhập vào TikTok",
                  style: GoogleFonts.oswald(
                    fontSize: 35,
                    color: Colors.black,
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(
                  height: 22,
                ),

                Text(
                  "Quản lí tài khoản, kiểm tra thông báo,\n"
                  "     bình luận trên các video, v.v.",
                  style: GoogleFonts.robotoCondensed(
                    fontSize: 16,
                      color: const Color(0xFF525252),
                    fontWeight: FontWeight.w500,
                  ),
                ),
                const SizedBox(
                  height: 22,
                ),

                Container(
                  width: MediaQuery.of(context).size.width,
                  margin: const EdgeInsets.symmetric(horizontal: 20),
                  child: InputTextWidget(
                    textEditingController: emailTextEditingController,
                    labelString: "Nhập vào email của bạn.",
                    iconData: Icons.email_rounded,
                    isObscure: false,
                  ),
                ),
                const SizedBox(
                  height: 22,
                ),

                Container(
                  width: MediaQuery.of(context).size.width,
                  margin: const EdgeInsets.symmetric(horizontal: 20),
                  child: InputTextWidget(
                    textEditingController: passwordTextEditingController,
                    labelString: "Nhập mật khẩu",
                    iconData: Icons.password_rounded,
                    isObscure: true,
                  ),
                ),
                const SizedBox(
                  height: 22,
                ),

                showProgressBar == false ?
                Column(
                  children: [
                    Container(
                      width: MediaQuery.of(context).size.width - 30,
                      height: 60,
                      decoration: const BoxDecoration(
                        color: Colors.black,
                        borderRadius: BorderRadius.all(
                          Radius.circular(15),
                        ),

                      ),
                      child:  InkWell(
                        onTap: (){
                          setState(() {
                            showProgressBar = true;

                          });

                          //Login user now


                        },
                        child: const Center(
                          child: Text(
                            "Tiếp theo",
                            style: TextStyle(
                              fontSize: 20,
                              color: Colors.white,
                              fontWeight: FontWeight.w700
                            ) ,
                          ),
                        ),
                        ),
                    ),
                    const SizedBox(
                      height: 22,
                    ),
                    //not have an account ? register now
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Text(
                          "Không có tài khoản?",
                          style: TextStyle(
                            fontSize: 16,
                            color: Color(0xFF535353)
                          ),
                        ),
                        InkWell(
                          onTap: ()
                          {
                            Get.to(() => RegistrationScreen());
                          },
                          child: const Text(
                            " Đăng ký ngay",
                            style: TextStyle(
                              fontSize: 16,
                              color: Colors.black,
                              fontWeight: FontWeight.bold,

                            ),
                          ),

                        )

                      ],
                    )

                  ],
                ) : Container(

                  child: const SimpleCircularProgressBar(
                    size: 50.0,
                    progressColors: [
                      Colors.green,
                      Colors.blueAccent,
                      Colors.red,
                      Colors.amber,
                      Colors.purpleAccent,
                    ],
                    animationDuration: 1,
                    backColor: Colors.black,
                  ),

                )



              ],
            ),
          )),
        );

      }
  }
