use std::io;
use rand::Rng;
use std::cmp::Ordering;

fn main() {
    println!("猜数字！");

    let secret_number = rand::thread_rng().gen_range(1..=100);

    loop {
        println!("请输入你的猜测：");
            
        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("读取输入失败");
    
        let guess: u32 = match guess.trim().parse() {
            Ok(num) => num,
            Err(_) => continue,
        };

        println!("你的猜测：{guess}");
    
        match guess.cmp(&secret_number) {
            Ordering::Less => println!("小了！"),
            Ordering::Greater => println!("大了！"),
            Ordering::Equal => {
                println!("恭喜！你猜中了🎉");
                break;
            }
        }
    }
}
