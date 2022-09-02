use std::thread::sleep;

pub struct Post {
    state: Option<Box<dyn State>>,
    content: String,
}

impl Post {
    pub fn new() -> Post {
        Post {
            state: Some(Box::new(Draft {})),
            content: String::new(),
        }
    }

    pub fn add_text(&mut self, text: &str) {
        self.content.push_str(text);
    }

    pub fn content(&self) -> &str {
        self.state.as_ref().unwrap().content(self)
    }

    fn state_change(&mut self, state_func: fn(Box<dyn State>) -> Box<dyn State>) {
        if let Some(state_value) = self.state.take() {
            self.state = Some(state_func(state_value))
        }
    }

    pub fn request_review(&mut self) {
        // if let Some(s) = self.state.take() {
        //     self.state = Some(s.request_review())
        // }
        self.state_change(State::request_review)
    }

    pub fn reject(&mut self) {
        // if let Some(s) = self.state.take() {
        //     self.state = Some(s.reject())
        // }
        self.state_change(State::reject)
    }

    pub fn approve(&mut self) {
        // if let Some(s) = self.state.take() {
        //     self.state = Some(s.approve())
        // }
        self.state_change(State::approve)
    }
}

trait State {
    fn request_review(self: Box<Self>) -> Box<dyn State>;
    fn reject(self: Box<Self>) -> Box<dyn State>;
    fn approve(self: Box<Self>) -> Box<dyn State>;
    fn content<'a>(&self, post: &'a Post) -> &'a str { "" }
}

struct Draft {}

impl State for Draft {
    fn request_review(self: Box<Self>) -> Box<dyn State> {
        Box::new(PendingReview {})
    }

    fn reject(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn approve(self: Box<Self>) -> Box<dyn State> {
        self
    }
}

struct PendingReview {}

impl State for PendingReview {
    fn request_review(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn reject(self: Box<Self>) -> Box<dyn State> {
        Box::new(Draft {})
    }

    fn approve(self: Box<Self>) -> Box<dyn State> {
        Box::new(Published {})
    }
}

struct Published {}

impl State for Published {
    fn request_review(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn reject(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn approve(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn content<'a>(&self, post: &'a Post) -> &'a str {
        &post.content
    }
}