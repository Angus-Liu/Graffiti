// let name = 'angus';
// let nameUpperCase = name.toUpperCase();
//
// console.log(name);
// console.log(nameUpperCase);

//
let people = {
  name: 'Angus',
  age: 22
};

console.log(people['name']);
console.log(JSON.stringify(people));

//
// console.log(people.name);
// people.name = undefined;
// console.log('name' in people);
// console.log(people.name);
// console.log('------------');
// delete people.name;
// console.log('name' in people);
// console.log(people.name);

// console.log(Object.keys({x: 0, y: 0, z: 2}));


// let journal = [];
//
// function addJournal(events, squirrel) {
//   journal.push({events, squirrel});
// }
//
// addJournal("eat", true);
// addJournal("run", false);
// addJournal(["eat", "run"], true);


// for (let entry of journal) {
//   console.log(entry);
// }
//
// journal.forEach(entry => console.log(entry));

// console.log(journal.shift());
//
// journal = journal.concat(journal);
// console.log(journal);


// console.log(String(67).padStart(5, '0'));
// console.log("Angus".repeat(3));
// console.log("abc"[1]);


// function max(...numbers) {
//   let res = -Infinity;
//   for (let num of numbers) {
//     res = res < num ? num : res;
//   }
//   return res;
// }
//
// console.log(max(1, 2, 3, 4, 5));
//
// let arr = [3, 4, 5, 6, 7];
// console.log(max(...arr));

