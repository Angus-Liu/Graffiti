fn main() {
    let mut s = String::from("Hello, world!");
    let len = calculate_length(&mut s);
    println!("The length of '{}' is '{}'", s, len);

    // let dp = dangling_pointer(); // error
    let no_dp = no_dangle();
}

fn calculate_length(s: &mut String) -> usize {
    s.push_str(" I'm E.T.");
    s.len()
}

// 悬挂指针
// fn dangling_pointer() -> &String {
//     let s = String::from("devil");
//     // s 所有权在函数内，代码执行完毕后 s 被释放，
//     // 此时返回的引用处于悬挂状态
//     &s
// }

fn no_dangle() -> String {
    let s = String::from("devil");
    s // s 的所有权被转移出去，不会被释放
}