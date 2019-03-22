// "Named Class Expression"（这是我自定义的术语，当前还没有官方术语来描述这个命名行为）
// let User = class MyClass {
//   sayHi() {
//     console.log(MyClass); // 类名 MyClass 只在类内部可见
//   }
// };
//
// new User().sayHi(); // 可以成功打印 MyClass 的定义
//
// console.log(User);
// console.log(MyClass); // 这样会报错，MyClass 在 class 外部并不可见

// class User {
//   static staticMethod() {
//     console.log(this === User);
//   }
// }
//
// User.staticMethod(); // true

//
// class Article {
//   constructor(title, date) {
//     this.title = title;
//     this.date = date;
//   }
//
//   static compare(articleA, articleB) {
//     return articleA.date - articleB.date;
//   }
// }
//
// // usage
// let articles = [
//   new Article("Mind", new Date(2016, 1, 1)),
//   new Article("Body", new Date(2016, 0, 1)),
//   new Article("JavaScript", new Date(2016, 11, 1))
// ];
//
// articles.sort(Article.compare);
//
// console.log( articles[0].title ); // Body

// class Animal {
//   stop() {
//     console.log("animal stop");
//   }
// }
//
// class Rabbit extends Animal {
//   stop() {
//     setTimeout(super.stop, 1000); // 1 秒后调用父类的 stop 函数
//   }
// }
//
// let rabbit = new Rabbit();
// rabbit.stop();


class Rabbit {
  static getName() {
    return null;
  }
}

// 通常我们调用 Object.getOwnPropertyNames
console.log( Rabbit.getName()); // a,b
