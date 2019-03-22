// function repeat(n, consume) {
//   for (let i = 0; i < n; i++) {
//     consume(i);
//   }
// }
//
// let labels = [];
// repeat(10, i => labels.push(i));
// console.log(labels);
//
//
// function greaterThan(n) {
//   return m => m > n;
// }
//
// let greaterThan10 = greaterThan(10);
//
// console.log(greaterThan10(1));
//
//
// function unless(test, then) {
//   if (test) then();
// }
//
// repeat(10, n => {
//   unless(n % 2 === 0, () => {
//     console.log(`${n} 是偶数`)
//   });
// });

// function filter(array, test) {
//   let res = [];
//   for (let item of array) {
//     if (test(item)) {
//       res.push(item);
//     }
//   }
//   return res;
// }
//
// let peoples = [{
//   name: "angus",
//   sex: "man"
// }, {
//   name: "tom",
//   sex: "man"
// }, {
//   name: "ally",
//   sex: "woman"
// }, {
//   name: "mary",
//   sex: "woman"
// }];
//
// let res = filter(peoples, item => item.sex === "woman");
// console.log(res);
//
// let people = {
//   name: 'Angus',
//   age: 22
// };
//
// for (let peopleKey in people) {
//   console.log(peopleKey);
// }

//
// function reduce(array, combine, start) {
//   let current = start;
//   for (let element of array) {
//     current = combine(current, element);
//   }
//   return current;
// }
//
// console.log(reduce([1, 2, 3, 4], (a, b) => a + b, 0));


// Two emoji characters, horse and shoe
// let horseShoe = "🐎🐎";
// console.log(horseShoe.length);
// // → 4
// console.log(horseShoe[0]);
// // → (Invalid half-character)
// console.log(horseShoe.charCodeAt(0));
// // → 55357 (Code of the half-character)
// console.log(horseShoe.codePointAt(0));
// // → 128052 (Actual code for horse emoji)
//
// for (let horseKey of horseShoe) {
//   console.log(horseKey);
// }


// // 展开
// let arrays = [[1, 2, 3], [4, 5], [6]];
// function flatmap(arr) {
//   return arr.reduce((a, b) => a.concat(b));
// }
//
// console.log(flatmap(arrays));
// // → [1, 2, 3, 4, 5, 6]
//
// // 你自己的循环
// loop(3, n => n < 10, n => n + 1, () => console.log("Hello World!"));
// function loop(n, predicate, update ,action) {
//   while (predicate(n)) {
//     action(n);
//     n = update(n);
//   }
// }
//
// // every
// function every(array, test) {
//   return !array.some(n => !test(n));
// }
//
// function every(array, test) {
//   for (let item of array) {
//     if (!test(item)) {
//       return false;
//     }
//   }
//   return true;
// }
//
// console.log(every([1, 3, 5], n => n < 10));
// // → true
// console.log(every([2, 4, 16], n => n < 10));
// // → false
// console.log(every([], n => n < 10));
// // → true
