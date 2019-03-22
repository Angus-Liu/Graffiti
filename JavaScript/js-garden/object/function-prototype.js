// let animal = {
//   eats: true
// };
//
// function Rabbit(name) {
//   this.name = name;
// }

// Rabbit.prototype = animal;

// let rabbit = new Rabbit("White Rabbit"); //  rabbit.__proto__ == animal
//
// console.log( rabbit.eats ); // true
// console.log( rabbit.__proto__ ); // true
// console.log( rabbit.prototype ); // true
// console.log( Object.getPrototypeOf(rabbit) ); // true

// function Rabbit() {}
// Rabbit.prototype = {
//   eats: true
// };
//
// let rabbit = new Rabbit();
//
// // Rabbit.prototype = {};
//
// Rabbit.prototype.eats = false;
//
// console.log(Rabbit.prototype);
// console.log(rabbit.eats); // undefined
// console.log(rabbit.__proto__);


// function Rabbit() {}
// Rabbit.prototype = {
//   eats: true
// };
//
// let rabbit = new Rabbit();
//
// // delete rabbit.eats;
//
// delete Rabbit.prototype.eats;
//
// console.log( rabbit.eats ); // ?

function User(name) {
  this.name = name;
}
User.prototype = {};

let user = new User('Angus');
console.log(user);
console.log(user.constructor);
let user2 = new user.constructor("Li");
console.log(user2.name);
