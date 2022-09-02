use crate::ApproveStage::{FIRST, SECOND};

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
        // 只允许博文处于 Draft 状态时增加文本内容。
        // 提示：让状态对象负责内容可能发生什么改变，但不负责修改 Post。
        if self.state.as_ref().unwrap().context_mutable() {
            self.content.push_str(text);
        }
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
        self.state_change(State::request_review)
    }

    pub fn reject(&mut self) {
        self.state_change(State::reject)
    }

    pub fn approve(&mut self) {
        self.state_change(State::approve)
    }
}

trait State {
    fn request_review(self: Box<Self>) -> Box<dyn State>;
    fn reject(self: Box<Self>) -> Box<dyn State>;
    fn approve(self: Box<Self>) -> Box<dyn State>;
    fn content<'a>(&self, post: &'a Post) -> &'a str { "" }
    fn context_mutable(&self) -> bool { false }
}

struct Draft {}

impl State for Draft {
    fn request_review(self: Box<Self>) -> Box<dyn State> {
        Box::new(PendingReview { approve_stage: FIRST })
    }

    fn reject(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn approve(self: Box<Self>) -> Box<dyn State> {
        self
    }

    fn context_mutable(&self) -> bool { true }
}

enum ApproveStage { FIRST, SECOND }

struct PendingReview {
    approve_stage: ApproveStage,
}

impl State for PendingReview {
    fn request_review(self: Box<Self>) -> Box<dyn State> {
        self
    }

    // 增加 reject 方法将博文的状态从 PendingReview 变回 Draft
    fn reject(self: Box<Self>) -> Box<dyn State> {
        Box::new(Draft {})
    }

    fn approve(mut self: Box<Self>) -> Box<dyn State> {
        // 在将状态变为 Published 之前需要两次 approve 调用
        match self.approve_stage {
            FIRST => {
                self.approve_stage = SECOND;
                self
            }
            SECOND => Box::new(Published {})
        }
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