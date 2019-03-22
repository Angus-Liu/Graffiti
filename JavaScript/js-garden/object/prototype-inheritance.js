// let animal = {
//   eats: true,
//   walk() {
//     console.log("Animal walk!");
//   }
// };
//
// let rabbit = {
//   jumps: true,
//   __proto__: animal
// };
//
// let longEar = {
//   earLength: 10,
//   __proto__: rabbit
// };
//
// longEar.walk();
// longEar.walk = () => {console.log("Long ear walk...")};
// longEar.walk();
// rabbit.walk();

// ============================
// let user = {
//   name: 'Angus',
//   surname: 'Liu',
//
//   set fullName(value) {
//     [this.name, this.surname] = value;
//   },
//
//   get fullName() {
//     return `${this.name} ${this.surname}`;
//   }
// };
//
// let admin = {
//   __proto__: user,
//   isAdmin: true
// };
//
// console.log(user.fullName);
// console.log(admin.fullName);
// admin.fullName = ['An', 'Li'];
// console.log(user.fullName);
// console.log(admin.fullName);

// ============================
// let animal = {
//   walk() {
//     if (!this.isSleeping) {
//       console.log(`I walk`);
//     }
//   },
//
//   sleep() {
//     this.isSleeping = true;
//   }
// };
//
// let rabbit = {
//   name: 'White rabbit',
//   __proto__: animal
// };
//
// rabbit.sleep();
// console.log(rabbit.isSleeping);
// console.log(animal.isSleeping);

// ============================
// let animal = {
//   jumps: null
// };
// let rabbit = {
//   __proto__: animal,
//   jumps: true
// };
//
// console.log( rabbit.jumps ); // true
//
// delete rabbit.jumps;
// console.log( rabbit.jumps ); // null
//
// delete animal.jumps;
// console.log( rabbit.jumps ); // undefined

// ======================
let hamster = {
  stomach: [],

  eat(food) {
    this.stomach = [food];
  }
};

let speedy = {
  __proto__: hamster
};

let lazy = {
  __proto__: hamster
};

speedy.eat('apple');
console.log(speedy.stomach);
console.log(lazy.stomach);

