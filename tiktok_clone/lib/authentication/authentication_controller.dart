import 'dart:io';

import 'package:get/get.dart';
import 'package:image_picker/image_picker.dart';

class AuthenticationController extends GetxController
{
  static AuthenticationController instanceAuth = Get.put(AuthenticationController());

  late Rx<File?> _pickedFile;
  File? get profileImage => _pickedFile.value;

  void chooseImageFromGallery() async
  {
    final pickImageFile = await ImagePicker().pickImage(source: ImageSource.gallery);


    if(pickImageFile != null)
    {
      Get.snackbar(
        "Ảnh đại diện",
        "Bạn đã chọn thành công ảnh đại diện."
      );

    }

    _pickedFile = Rx<File?> (File(pickImageFile!.path));
  }
  void captureImageWithCamera() async
  {
    final pickImageFile = await ImagePicker().pickImage(source: ImageSource.camera);


    if(pickImageFile != null)
    {
      Get.snackbar(
          "Ảnh đại diện",
          "Bạn đã chọn thành công ảnh đại diện."
      );

    }

    _pickedFile = Rx<File?> (File(pickImageFile!.path));
  }

  void createAccountForNewUser(File imageFile, String userName, String userEmail, userPassword)
  {

  }

}