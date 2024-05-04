import 'package:flutter/material.dart';

class InputTextWidget extends StatelessWidget {
  final TextEditingController textEditingController;
  final IconData? iconData;
  final String? assetRefrence;
  final String labelString;
  final bool isObscure;


  const InputTextWidget({super.key,
    required this.textEditingController,
    this.iconData,
    this.assetRefrence,
    required this.labelString,
    required this.isObscure
});



  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: textEditingController,
      style: const TextStyle(color: Colors.black), // Here!
      decoration: InputDecoration(
        labelText: labelString,
        prefixIcon: iconData != null
            ? Icon(iconData, color: Colors.black)
            : Padding(
                padding : const EdgeInsets.all(15),
                child: Image.asset(assetRefrence!,width: 10,),

        ),
        labelStyle
            : const TextStyle(
            color: Color(0xFF808080),
          fontSize: 15,
          fontWeight: FontWeight.w600
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(20),
          // borderSide: const BorderSide(
          //   color: Colors.green,
          // )
        ),
        focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(20),

            // borderSide: const BorderSide(
            //   color: Colors.green,
            // )
          )

      ),
      obscureText: isObscure,

    );
  }
}
