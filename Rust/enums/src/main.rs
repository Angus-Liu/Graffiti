#[derive(Debug)]
enum IpAddr {
    V4(u8, u8, u8, u8),
    V6(String),
}

#[derive(Debug)]
enum UsState {
    Alabama,
    Alaska,
}

#[derive(Debug)]
enum Coin {
    Penny,
    Nickel,
    Dime,
    Quarter(UsState),
}

fn value_in_cents(coin: &Coin) -> u8 {
    match coin {
        Coin::Penny => {
            println!("Lucky Penny!");
            1
        }
        Coin::Nickel => 5,
        Coin::Dime => 10,
        Coin::Quarter(state) => {
            println!("State quarter from {:?}!", state);
            25
        }
    }
}

fn main() {
    // let home = IpAddr::V4(127, 0, 0, 1);
    // let loopback = IpAddr::V6(String::from("::1"));
    //
    // dbg!(home);
    // dbg!(loopback);
    //
    // let some_num: Option<i8> = Some(5);
    // let x: i8 = 5;
    // let sum = x + some_num.unwrap_or(6);
    // dbg!(sum);

    let coin = Coin::Penny;
    let cents = value_in_cents(&coin);
    println!("The cents of '{:?}' is {}.", coin, cents);

    println!();

    let coin2 = Coin::Quarter(UsState::Alabama);
    let cents2 = value_in_cents(&coin2);
    println!("The cents of '{:?}' is {}.", coin2, cents2);

    println!();

    if let Coin::Quarter(state) = coin {
        println!("The state of coin is {:?}.", state);
    } else {
        println!("No state info!")
    }

    if let Coin::Quarter(state) = coin2 {
        println!("The state of coin2 is {:?}.", state);
    }
}
