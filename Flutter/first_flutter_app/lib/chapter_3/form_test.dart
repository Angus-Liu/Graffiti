import 'package:flutter/material.dart';

class FormTest extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _FormTestState();
}

class _FormTestState extends State<FormTest> {
  TextEditingController _unameController = TextEditingController();
  TextEditingController _pwdController = TextEditingController();
  GlobalKey _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: Scaffold(
        appBar: AppBar(
          title: Text("Form Test"),
        ),
        body: Padding(
          padding: EdgeInsets.symmetric(vertical: 16.0, horizontal: 24.0),
          child: Form(
            key: _formKey,
            autovalidateMode: AutovalidateMode.always,
            child: Column(
              children: [
                TextFormField(
                  autofocus: true,
                  controller: _unameController,
                  decoration: InputDecoration(
                      labelText: "用户名",
                      hintText: "用户名或邮箱",
                      icon: Icon(Icons.person)),
                  validator: (v) => v.trim().length > 0 ? null : "用户名不能为空",
                ),
                TextFormField(
                  controller: _pwdController,
                  decoration: InputDecoration(
                      labelText: "密码",
                      hintText: "你的登录密码",
                      icon: Icon(Icons.lock)),
                  validator: (v) => v.trim().length > 5 ? null : "密码不能少于 5 位",
                ),
                Padding(
                  padding: EdgeInsets.only(top: 28),
                  child: Row(
                    children: [
                      Expanded(
                          child: ElevatedButton(
                        style: ButtonStyle(
                            padding:
                                MaterialStateProperty.all(EdgeInsets.all(15)),
                            backgroundColor: MaterialStateProperty.all(
                                Theme.of(context).primaryColor),
                            foregroundColor:
                                MaterialStateProperty.all(Colors.white)),
                        child: Text("登录"),
                        onPressed: () {
                          if ((_formKey.currentState as FormState).validate()) {
                            print('校验通过');
                          } else {
                            print('校验失败');
                          }
                        },
                      ))
                    ],
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
