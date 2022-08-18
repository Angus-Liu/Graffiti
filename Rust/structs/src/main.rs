fn main() {
    let mut user1 = User {
        active: true,
        username: String::from("Angus"),
        email: String::from("angus.liu86@qq.com"),
        sign_in_count: 1,
    };
    user1.email = String::from("test@qq.com");

    let user2 = User {
        email: String::from("user2@qq.com"),
        ..user1
    };
    // println!("{}", user1.username);
    println!("{}", user2.username);

    let black = Color(0, 0, 0);
    println!("{}", black.0);
}

struct Color(i32, i32, i32);

struct User {
    active: bool,
    username: String,
    email: String,
    sign_in_count: u64,
}

fn build_user(email: String, username: String) -> User {
    User {
        active: true,
        username,
        email,
        sign_in_count: 1,
    }
}