use std::fmt::{Debug, Display, Formatter};
use std::iter::Sum;

pub fn add(left: usize, right: usize) -> usize {
    left + right
}

pub trait Summary {
    fn summarize_author(&self) -> String;

    fn summarize(&self) -> String;

    fn default_summarize(&self) -> String {
        format!("(Read more from {}...)", self.summarize_author())
    }
}

pub struct NewsArticle {
    pub headline: String,
    pub location: String,
    pub author: String,
    pub content: String,
}

impl Summary for NewsArticle {
    fn summarize_author(&self) -> String {
        format!("@{}", self.author)
    }

    fn summarize(&self) -> String {
        format!("{}, by {} ({})", self.headline, self.author, self.location)
    }
}

pub struct Tweet {
    pub username: String,
    pub content: String,
    pub reply: bool,
    pub retweet: bool,
}

impl Summary for Tweet {
    fn summarize_author(&self) -> String {
        format!("@{}", self.username)
    }

    fn summarize(&self) -> String {
        format!("{}: {}", self.username, self.content)
    }
}

pub fn notify(item: &impl Summary) {
    println!("Breaking news! {}", item.summarize());
}

fn some_func<T, U>(t: &T, u: &U) -> i32
    where T: Display + Clone,
          U: Clone + Debug
{
    0
}

fn returns_summarizable() -> impl Summary {
    Tweet {
        username: String::from("horse_ebooks"),
        content: String::from(
            "of course, as you probably already know, people",
        ),
        reply: false,
        retweet: false,
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }

    #[test]
    fn test_summarize() {
        let news = NewsArticle {
            headline: "Hello World!".to_string(),
            location: "Cheng Du".to_string(),
            author: "Angus".to_string(),
            content: "Hello, I'm Robot A!".to_string(),
        };
        let sum_info = news.summarize();
        println!("sum_info: {}", sum_info);
        println!("{}", news.default_summarize());

        let tweet = Tweet {
            username: "Li Lei".to_string(),
            content: "I'm Li Lei!".to_string(),
            reply: false,
            retweet: false,
        };
        let sum_info = tweet.summarize();
        println!("sum_info: {}", sum_info);
        println!("{}", tweet.default_summarize());

        let tweet = Tweet {
            username: String::from("horse_ebooks"),
            content: String::from(
                "of course, as you probably already know, people",
            ),
            reply: false,
            retweet: false,
        };
        println!("1 new tweet: {}", tweet.summarize());
        println!("{}", tweet.default_summarize());

        notify(&tweet);

        let s = returns_summarizable();
        s.summarize();
    }
}
