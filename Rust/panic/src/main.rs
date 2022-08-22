use io::Error;
use std::fs::File;
use std::io;
use std::io::Read;

fn main() {
    // panic!("Error");

    // index out of bounds
    // let v = vec![1, 2, 3];
    // v[99];


    // Result
    // let file_path = "hello.txt";
    // let f = match File::open(file_path) {
    //     Ok(file) => file,
    //     Err(error) => match error.kind() {
    //         ErrorKind::NotFound => match File::create(file_path) {
    //             Ok(fc) => fc,
    //             Err(e) => panic!("Problem creating the file: {:?}", e),
    //         }
    //         other_error => {
    //             panic!("Problem opening the file: {:?}", other_error)
    //         }
    //     }
    // };

    let file_path = "hello.txt";
    let username = read_username_from_file(file_path).unwrap();
    println!("username: {}", username);
}

fn read_username_from_file(filename: &str) -> Result<String, Error> {
    // let mut f = match File::open(filename) {
    //     Ok(file) => file,
    //     Err(e) => return Err(e),
    // };
    // let mut f = File::open(filename)?;
    // let mut buf = String::new();
    // f.read_to_string(&mut buf)?;
    // Ok(buf)

    let mut buf = String::new();
    File::open(filename)?.read_to_string(&mut buf)?;
    Ok(buf)
}
