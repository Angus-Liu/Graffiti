// 假设具有 canEat 属性的对象为动物类
// class Animal {
  // static [Symbol.hasInstance](obj) {
  //   if (obj.canEat) return true;
  // }
// }

// let obj = new Animal();
// console.log(obj instanceof Animal);

// function Rabbit() {}

// 保存 toString 方法的引用，方便后面使用
// let objectToString = Object.prototype.toString;
//
// 猜猜是什么类型？
// let arr = [];
//
// console.log( objectToString.call(arr) ); // [object Array]
//
// console.log(typeof  arr);

// 修改 Object.prototype
Object.prototype.bar = 1;

const foo = {moo: 2};
for(const i in foo) {
  console.log(i); // 输出两个属性：bar 和 moo
}
