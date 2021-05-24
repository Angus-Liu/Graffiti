import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  BuildContext _context;

  // @override
  // Widget build(BuildContext context) {
  //   _context = context;
  //
  //   return Container(
  //     decoration: BoxDecoration(color: Colors.white),
  //     child: Center(
  //       child: Text(
  //         'Hello World',
  //         textDirection: TextDirection.ltr,
  //         style: TextStyle(color: Colors.black),
  //       ),
  //     ),
  //   );
  // }

  @override
  Widget build(BuildContext context) {
    _context = context;

    Widget titleSection = Container(
      padding: const EdgeInsets.all(32),
      child: Row(
        children: [
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  padding: const EdgeInsets.only(bottom: 8),
                  child: Text(
                    'Oeschinen Lake Campground',
                    style: TextStyle(fontWeight: FontWeight.bold),
                  ),
                ),
                Text(
                  'Kandersteg, Switzerland',
                  style: TextStyle(color: Colors.grey[500]),
                )
              ],
            ),
          ),
          Icon(Icons.star, color: Colors.red[500]),
          Text("41")
        ],
      ),
    );

    Widget buttonSection = Container(
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: [
          buildButtonColumn(Icons.call, "CALL"),
          buildButtonColumn(Icons.near_me, "ROUTE"),
          buildButtonColumn(Icons.share, "SHARE")
        ],
      ),
    );

    Widget textSection = Container(
      padding: const EdgeInsets.all(32),
      child: Text(
          '''Lake Oeschinen lies at the foot of the Blüemlisalp in the Bernese Alps. Situated 1,578 meters above sea level, it is one of the larger Alpine Lakes. A gondola ride from Kandersteg, followed by a half-hour walk through pastures and pine forest, leads you to the lake, which warms to 20 degrees Celsius in the summer. Activities enjoyed here include rowing, and riding the summer toboggan run.''',
          softWrap: true),
    );

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: Scaffold(
          body: ListView(
        children: [
          Image.asset(
            'images/lake.jpg',
            width: 600,
            height: 240,
            fit: BoxFit.cover,
          ),
          titleSection,
          buttonSection,
          textSection
        ],
      )),
    );
  }

  Column buildButtonColumn(IconData icon, String label) {
    Color color = Theme.of(_context).primaryColor;
    return Column(
      mainAxisSize: MainAxisSize.min,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        IconButton(icon: Icon(icon, color: color), onPressed: () => {}),
        Center(
          child: Container(
            margin: EdgeInsets.only(top: 8),
            child: Text(
              label,
              style: TextStyle(
                  fontSize: 12, fontWeight: FontWeight.w400, color: color),
            ),
          ),
        )
      ],
    );
  }
}
