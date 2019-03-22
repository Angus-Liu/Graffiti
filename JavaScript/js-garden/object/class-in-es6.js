// function User(name, birthday) {
//   // 只对User内的其他方法可见
//   function calcAge() {
//     return new Date().getFullYear() - birthday.getFullYear();
//   }
//
//   this.sayHi = function() {
//     console.log(`${name}, age:${calcAge()}`);
//   };
// }
//
// let user = new User("John", new Date(2000, 0, 1));
// user.sayHi(); // John, age:17

function Animal(name) {
  this.name = name;
}

Animal.prototype.walk = function() {
  alert(this.name + ' walks');
};

function Rabbit(name) {
  this.name = name;
}

Rabbit.prototype.__proto__ = Animal.prototype;

Rabbit.prototype.walk = function() {
  console.log(this.name + " bounces!");
};
