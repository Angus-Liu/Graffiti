// let obj = {};
// console.log(Object.getPrototypeOf(obj) === Object.prototype);
// console.log(Object.getPrototypeOf(Object.prototype));


// let arr = [1, 2, 3,];
// console.log(Object.getPrototypeOf(arr) === Array.prototype);
// console.log(Object.getPrototypeOf(arr.__proto__) === Object.prototype);
//
// function f(){}
// console.log(Object.getPrototypeOf(f) === Function.prototype);

// let arr = new Array(4);
// console.log(arr);
// console.log(arr.join("ans"));


// function showArgs() {
//   console.log(Array.prototype.join.call(arguments, '-'));
// }
//
// showArgs("angus", "liu", "23");


// function hello() {
//   console.log('Hello');
// }
//
// function world() {
//   console.log('world');
// }
//
// Function.prototype.defer = function (millisec) {
//   setTimeout(this, millisec);
// };
//
// hello.defer(1000);
// world.defer(100);


function f(a, b) {
  console.log(a + b);
}

Function.prototype.defer = function (ms) {
  let f = this;

  return function (a, b) {
    setTimeout(() =>f(a, b), 1000);
  };
};

f.defer(5000)(1, 2);
