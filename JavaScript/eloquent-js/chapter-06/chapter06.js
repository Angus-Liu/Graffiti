// function normalize() {
//   console.log(this.coords.map(n => n / this.length));
// }
//
// normalize.call({coords: [0, 2, 3], length: 5});
// // → [0, 0.4, 0.6]
//
//
// let empty = {};
// console.log(empty.toString);
//
//
// let protoRabbit = {
//   speak(line) {
//     console.log(`The ${this.name} rabbit says '${line}'`);
//   }
// };
//
// let killerRabbit = Object.create(protoRabbit);
// killerRabbit.name = "killer";
// killerRabbit.speak("Hello");


// function Rabbit(type) {
//   this.type = type;
// }
//
// Rabbit.prototype.speak = function(line) {
//   console.log(`The ${this.type} rabbit says '${line}'`);
// };
//
// let littleRabbit = new Rabbit("little");
// littleRabbit.speak("Hello World!");
//
// console.log(Object.getPrototypeOf(littleRabbit) == Rabbit.prototype);
// console.log(Object.getPrototypeOf(Rabbit) == Function.prototype);
//
// let obj = new class {
//   hello() {
//     console.log("Hello");
//   }
// };
//
// console.log(Object.getPrototypeOf(obj));
// obj.hello();


class Rabbit {
  constructor(type) {
    this.type = type;
  }

  speak(line) {
    console.log(`The ${this.type} rabbit says '${line}'`);
  }

  toString() {
    return `a ${this.type} rabbit`;
  }
}

let killerRabbit = new Rabbit("killer");
let blackRabbit = new Rabbit("black");

console.log(String(killerRabbit));
