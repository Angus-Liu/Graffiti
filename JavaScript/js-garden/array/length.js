// let foo = [1, 2, 3, 4, 5, 6];
// console.log(foo);
// foo.length = 3;
// console.log(foo);
// foo.length = 6;
// console.log(foo);
//
// console.log(5 in foo);
// foo[5] = undefined;
// console.log(5 in foo);

function Foo() {
}

function Bar() {
}

Bar.prototype = new Foo();

console.log(new Bar() instanceof Bar); // true
console.log(new Bar() instanceof Foo);// true

// 如果仅仅设置 Bar.prototype 为函数 Foo 本身，而不是 Foo 构造函数的一个实例
console.log(Bar.prototype.__proto__.__proto__);
Bar.prototype = Foo;
console.log(new Bar() instanceof Foo);// false
