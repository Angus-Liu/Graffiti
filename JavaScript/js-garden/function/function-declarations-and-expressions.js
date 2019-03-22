// function Foo() {
//
// }
//
// Foo.method = function() {
//   console.log(this);
//   function test() {
//     // 使用 that 来指向 Foo 对象
//     console.log(this);
//   }
//   test();
// };
//
// Foo.method();


// (function say(word) {
//   console.log(word);
// })("Hello");

let i = 1;

((i) => {
  setTimeout(function() {
    console.log(i);
  }, 1000);
})(i);


i = 2;

((i) => {
  setTimeout(function() {
    console.log(i);
  }, 1000);
})(i);


