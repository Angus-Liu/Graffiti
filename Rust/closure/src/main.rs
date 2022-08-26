use std::collections::HashMap;
use std::hash::Hash;
use std::thread;
use std::time::Duration;

struct Cacher<T, K, V>
    where T: Fn(K) -> V,
          K: Eq + Hash + Clone,
          V: Clone
{
    calculation: T,
    // value: Option<u32>,
    value_map: HashMap<K, V>,
}

impl<T, P, R> Cacher<T, P, R>
    where T: Fn(P) -> R,
          P: Eq + Hash + Clone,
          R: Clone
{
    fn new(calculation: T) -> Cacher<T, P, R> {
        Cacher {
            calculation,
            // value: None,
            value_map: HashMap::new(),
        }
    }

    fn value(&mut self, arg: P) -> R {
        // match self.value {
        //     Some(v) => v,
        //     None => {
        //         let v = (self.calculation)(arg);
        //         self.value = Some(v);
        //         v
        //     }
        // }
        match self.value_map.get(&arg) {
            Some(v) => v.clone(),
            None => {
                let r = (self.calculation)(arg.clone());
                self.value_map.insert(arg.clone(), r.clone());
                r
            }
        }
    }
}

fn generate_workout(intensity: u32, random_number: u32) {
    let mut expensive_result = Cacher::new(|num| {
        println!("calculating slowly...");
        thread::sleep(Duration::from_secs(2));
        num
    });
    if intensity < 25 {
        println!("Today, do {} push-ups!", expensive_result.value(intensity));
        println!("Next, do {} sit-ups!", expensive_result.value(intensity));
    } else if random_number == 3 {
        println!("Take a break today! Remember to stay hydrated!");
    } else {
        println!("Today, run for {} minutes!", expensive_result.value(intensity));
    }
}

fn main() {
    // let simulated_user_specified_value = 10;
    // let simulated_random_number = 7;
    //
    // generate_workout(simulated_user_specified_value, simulated_random_number);

    let x = vec![1, 2, 3];
    let equal_to_x = move |z| z == x;
    // println!("can't use x here: {:?}", x);

    let y = vec![1, 2, 3];
    assert!(equal_to_x(y));
}

fn hello(name: &str) -> String {
    format!("Hello! {} :)", name)
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn call_with_different_values() {
        let mut c1 = Cacher::new(|a| a);

        let v1 = c1.value(1);
        assert_eq!(v1, 1);

        let v2 = c1.value(2);
        assert_eq!(v2, 2);

        let mut c2 = Cacher::new(|a: &str| a.len());
        let len2 = c2.value("hello world");
        assert_eq!(len2, 11);

        let mut c3 = Cacher::new(hello);
        let greet = c3.value("Angus");
        println!("{}", greet);
    }
}
