fn main() {
    // let number = 30;
    //
    //  if number < 5 {
    //      println!("condition was true");
    //  } else {
    //      println!("condition was false");
    //  }
    //
    //  let mut counter = 0;
    //  let result = loop {
    //      println!("again!");
    //      counter += 1;
    //      if counter == 10 { break counter * 2; }
    //  };
    //
    //  println!("The result id {result}");

    // let mut count = 0;
    // 'counting_up: loop {
    //     println!("count = {count}");
    //     let mut remaining = 10;
    //
    //     loop {
    //         println!("remaining = {remaining}");
    //         if remaining == 9 {
    //             break;
    //         }
    //         if count == 2 {
    //             break 'counting_up;
    //         }
    //         remaining -= 1;
    //     }
    //     count += 1;
    // }
    // println!("End count = {count}");

    let mut number = 3;
    while number != 0 {
        println!("{number}");
        number -= 1;
    }

    let arr = [20, 30, 30, 40, 10, 200];
    let len = arr.len();
    println!("len = {}", len);
    for item in arr {
        println!("{}", item);
    }

    fib(10);
}

fn fib(mut counter: u8) {
    let mut i = 0;
    let mut j = 1;
    while counter > 0 {
        j = i + j;
        i = j - i;
        println!("j = {}", j);
        counter -= 1;
    }
}
