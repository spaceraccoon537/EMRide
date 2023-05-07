import 'package:flutter/material.dart';
import 'quotes.dart';

void main()=> runApp(MaterialApp(
    home: ninja(),
  ));

class ninja extends StatefulWidget {
  const ninja({Key? key}) : super(key: key);

  @override
  State<ninja> createState() => _ninjaState();
}

class _ninjaState extends State<ninja> {
  @override
  List<Quotes> q=[
    Quotes("I'm not here to be perfect, I'm here to be real." , "Lady Gaga"),
    Quotes("I'm not interested in money. I just want to be wonderful." , "Marilyn Monroe"),
    Quotes("The only thing that feels better than winning is winning when nobody thought you could." , "Hank Aaron"),
    Quotes("Success is not final, failure is not fatal: It is the courage to continue that counts." , "Winston Churchill"),
    Quotes("If you can dream it, you can do it." , "Walt Disney"),
    Quotes("If you want something done, ask a busy person to do it." , "Laura Ingalls Wilder"),
    Quotes("If your actions inspire others to dream more, learn more, do more and become more, you are a leader." , "John Quincy Adams"),
    Quotes("The best way to find out if you can trust somebody is to trust them." , "Ernest Hemingway")
  ];

  Widget quoteTemplate(i){
    return Card(
      margin: EdgeInsets.fromLTRB(16, 16, 16, 0),
      child: Padding(
        padding: const EdgeInsets.all(10.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Text(
              i.text,
              style: TextStyle(
                fontSize: 16,
                color: Colors.grey[600],
              ),
            ),
            SizedBox(height: 8),
            Text(
              i.author,
              style: TextStyle(
                fontSize: 12,
                color: Colors.grey[800],
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: AppBar(
        title: Text("New App"),
        centerTitle: true,
        backgroundColor: Colors.redAccent,
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: q.map((i){
          return quoteTemplate(i);
        }).toList(),
      ),
    );
  }
}




