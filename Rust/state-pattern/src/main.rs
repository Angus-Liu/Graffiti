use state_pattern::Post;

fn main() {
    let mut post = Post::new();

    post.add_text("I ate a salad for lunch today");
    assert_eq!("", post.content());

    post.request_review();
    assert_eq!("", post.content());

    // 模拟审核到一阶段然后被拒绝
    post.approve();
    post.reject();
    post.request_review();
    assert_eq!("", post.content());

    // 两阶段审核通过
    post.approve();
    post.approve();
    post.add_text("invalid");
    assert_eq!("I ate a salad for lunch today", post.content());

    post.reject();
    assert_eq!("I ate a salad for lunch today", post.content());
}
