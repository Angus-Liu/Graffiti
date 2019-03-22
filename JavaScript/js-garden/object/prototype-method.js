// let o = Object.create(null);
// console.log(Object.getPrototypeOf(o));


// let animal = {
//   eats: true
// };
//
// let rabbit = Object.create(animal);
// console.log(Object.getPrototypeOf(rabbit) === animal);
// // Object.setPrototypeOf(rabbit, {});
// console.log(rabbit.eats);
//
// console.log(Object.getOwnPropertyDescriptors(rabbit));
//
// rabbit.__proto__ = "1231";
// // Object.setPrototypeOf(rabbit, "123131");
// console.log(rabbit.__proto__);
//
// let rabbit = {
//   jumps: true,
//   __proto__: animal
// };
//
// console.log(Object.keys(rabbit));
// for (let prop in rabbit) {
//   // if (rabbit.hasOwnProperty(prop)) {
//   console.log(prop);
//   // }
// }


// let dictionary = Object.create(null, {
//   toString: { // 定义 toString 方法
//     value() { // value 是一个函数
//       return Object.keys(this).join();
//     }
//   }
// });
//
// dictionary.apple = "Apple";
// dictionary.__proto__ = "test";
//
// // apple 和 __proto__ 在循环内
// for(let key in dictionary) {
//   console.log(key); // "apple"，然后 "__proto__"
// }
//
// // 通过 toString 得到逗号分隔的属性值
// console.log(dictionary.toString()); // "apple,__proto__"


function Rabbit(name) {
  this.name = name;
}

Rabbit.prototype.sayHi = function () {
  console.log(this.name);
};

let rabbit = new Rabbit("rabbit");

rabbit.sayHi(); // rabbit
Rabbit.prototype.sayHi(); // undefined
Object.getPrototypeOf(rabbit).sayHi(); // undefined
rabbit.__proto__.sayHi(); // undefined
