import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:simple_circular_progress_bar/simple_circular_progress_bar.dart';
import 'package:tiktok_clone/authentication/authentication_controller.dart';
import 'package:tiktok_clone/authentication/login_screen.dart';

import '../widgets/input_text_widget.dart';

class RegistrationScreen extends StatefulWidget {
  const RegistrationScreen({super.key});

  @override
  State<RegistrationScreen> createState() => _RegistrationScreenState();
}

class _RegistrationScreenState extends State<RegistrationScreen> {
  TextEditingController usernameTextEditingController = TextEditingController();
  TextEditingController emailTextEditingController = TextEditingController();
  TextEditingController passwordTextEditingController = TextEditingController();
  bool showProgressBar = false;


  var authenticationController = AuthenticationController.instanceAuth;




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


                const SizedBox(
                  height: 50,
                ),

                Text(
                  "Đăng ký tài khoản. ",
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

                  "Tạo hồ sơ, follow các tài khoản khác,quay\n"
                      "video của chính bạn, v.v. ",
                  style: GoogleFonts.robotoCondensed(
                    fontSize: 16,
                    color: const Color(0xFF525252),
                    fontWeight: FontWeight.w500,
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(
                  height: 20,
                ),

                //profile avatar
                GestureDetector(
                  onTap: (){
                    authenticationController.chooseImageFromGallery();


                  },
                  child: const CircleAvatar(
                    radius: 70,
                    backgroundImage: AssetImage(
                      "images/avatar.gif"

                    ),

                    backgroundColor: Colors.white,
                  ),
                ),

                Text(

                  "bấm để chọn ảnh đại diện.",
                  style: GoogleFonts.robotoCondensed(
                    fontSize: 16,
                    color: const Color(0xFF525252),
                    fontWeight: FontWeight.w500,
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(
                  height: 20,
                ),

                //name input
                Container(
                  width: MediaQuery.of(context).size.width,
                  margin: const EdgeInsets.symmetric(horizontal: 20),
                  child: InputTextWidget(
                    textEditingController: usernameTextEditingController,
                    labelString: "Hãy chọn cho bạn 1 cái tên đăng nhập!",
                    iconData: Icons.person_2_outlined,
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
                    labelString: "Nhập mật khẩu.",
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


                          if(authenticationController.profileImage != null
                              && usernameTextEditingController.text.isNotEmpty
                              && emailTextEditingController.text.isNotEmpty
                              && passwordTextEditingController.text.isNotEmpty)
                          {
                            setState(() {
                              showProgressBar = true;

                            });

                            authenticationController.createAccountForNewUser(
                                authenticationController.profileImage!,
                                usernameTextEditingController.text,
                                emailTextEditingController.text,
                                passwordTextEditingController.text
                            );
                          }

                          //sign-up user now


                        },
                        child: const Center(
                          child: Text(
                            "Đăng ký ngay",
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
                          "Đã có tài khoản?",
                          style: TextStyle(
                              fontSize: 16,
                              color: Color(0xFF535353)
                          ),
                        ),
                        InkWell(
                          onTap: ()
                          {
                            Get.to(() => LoginScreen());
                          },
                          child: const Text(
                            " Đăng nhập",
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
