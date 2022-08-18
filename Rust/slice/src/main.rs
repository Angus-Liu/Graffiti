fn main() {
    let s = String::from("Hello World!");
    let f = first_word(&s);
    println!("{}", f);

    let a = [1, 2, 3, 4, 5];
    let slice = &a[1..4];
    assert_eq!(slice, &[2, 3, 4]);
}

// fn first_word(s: &String) -> usize {
//     let bytes = s.as_bytes();
//
//     for (i, &item) in bytes.iter().enumerate() {
//         if item == b' ' {
//             return i;
//         }
//     }
//     s.len()
// }

fn first_word(s: &str) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }
    &s[..]
}