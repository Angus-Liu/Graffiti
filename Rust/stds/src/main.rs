use std::collections::HashMap;

fn vec() {
    // let mut v: Vec<i32> = Vec::new();
    // for i in 1..=10 {
    //     v.push(i);
    // }
    // println!("{:?}", v);
    //
    // let v2 = vec!["Amy", "Bob"];
    // println!("{:?}", v2);

    let mut v = vec![1, 2, 3, 4, 5];

    let third: &i32 = &v[2];
    println!("The third element is {}", third);

    match v.get(2) {
        Some(third) => println!("The third element is {}", third),
        None => println!("There is no third element."),
    }

    for i in &mut v {
        *i += 50;
    }

    for (i, e) in v.iter().enumerate() {
        println!("{} - {}", i, e);
    }

    #[derive(Debug)]
    enum SpreadsheetCell {
        Int(i32),
        Float(f64),
        Text(String),
    }

    let row = vec![
        SpreadsheetCell::Int(3),
        SpreadsheetCell::Text(String::from("blue")),
        SpreadsheetCell::Float(10.12),
    ];

    println!("{:?}", row);
}

fn str() {
    let mut s = String::from("foo");
    s.push_str("bar");

    let s2 = String::from("s2");

    let s3 = s.clone() + &s2;
    println!("{}", s);
    println!("{}", s2);
    println!("{}", s3);

    let hello = "Здравствуйте";
    for c in hello.chars() {
        println!("{}", c);
    }
}

fn main() {
    let mut scores = HashMap::new();
    scores.insert("key", 12);
    scores.insert("key", 12);

    let teams = vec![String::from("Blue"), String::from("Yellow")];
    let initial_scores = vec![10, 50];

    let mut scores: HashMap<_, _> =
        teams.into_iter().zip(initial_scores.into_iter()).collect();
    println!("{:?}", scores);

    let team_name = String::from("Blue");
    let score = match scores.get(&team_name) {
        Some(value) => value,
        None => &(-1),
    };
    println!("{}", score);


    scores.insert(team_name, 0);

    scores.entry(String::from("new")).or_insert(100);

    for (k, v) in &scores {
        println!("{}: {}", k, v);
    }

    let text = "hello world wonderful world";

    let mut map = HashMap::new();

    for word in text.split_whitespace() {
        let count = map.entry(word).or_insert(0);
        *count += 1;
    }

    println!("{:?}", map);
}
