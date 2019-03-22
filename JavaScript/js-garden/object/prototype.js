function Foo() {
  this.value = 42;
}

Foo.prototype = {
  method: function () {
  }
};

function Bar() {}

// 设置 Bar 的 prototype 属性为 Foo 的实例对象
Bar.prototype = new Foo();
Bar.prototype.foo = 'Hello World!';

// 修正 Bar.prototype.constructor 为 Bar 本身
Bar.prototype.constructor = Bar;

// 创建 Bar 的一个实例
var test = new Bar();


let user = {
  name: "angus"
};

let des = Object.getOwnPropertyDescriptor(user, 'name');
console.log(des);
Object.defineProperty(user, 'name', {
  writable: false
});
user.name = 'liu';
console.log(user.name);
