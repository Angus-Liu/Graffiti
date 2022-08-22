struct Point<T, U> {
    x: T,
    y: U,
}

impl Point<char, char> {
    fn show(&self) {
        println!("x = {}, y = {}", self.x, self.y);
    }
}

impl Point<f32, f32> {
    fn distance_from_origin(&self) -> f32 {
        (self.x.powi(2) + self.y.powi(2)).sqrt()
    }
}

impl<T, U> Point<T, U> {
    fn x(&self) -> &T {
        &self.x
    }

    fn m<E>(&self, e: &E) {}
}

fn largest<T: PartialOrd>(list: &[T]) -> &T {
    let mut largest = &list[0];
    for item in list {
        if item > largest {
            largest = item;
        }
    }
    largest
}

fn main() {
    // let i_i = Point { x: 2, y: 10 };
    // let f_f = Point { x: 1.0, y: 4.0 };
    // let i_f = Point { x: 1, y: 0.1 };

    let p = Point { x: 5, y: 10 };
    println!("p.x = {}", p.x());
    p.m::<i32>(&10);

    let cp = Point { x: 'o', y: 'k' };
    cp.show();

    let fp = Point { x: 4.0, y: 3.0 };
    let dist = fp.distance_from_origin();
    println!("dist = {}", dist);

    let number_list = vec![34, 50, 25, 100, 65];

    let result = largest(&number_list);
    println!("The largest number is {}", result);

    let char_list = vec!['y', 'm', 'a', 'q'];

    let result = largest(&char_list);
    println!("The largest char is {}", result);
}
