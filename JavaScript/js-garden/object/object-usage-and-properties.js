false.toString(); // 'false'
[1, 2, 3].toString();
console.log('--------');

function Foo() {
}

Foo.bar = 1;
console.log(Foo.bar);
console.log('--------');

console.log(2..toString());
// console.log(2 .toString());
console.log((2).toString());
console.log('--------');

var foo = {}; // 一个空对象
console.log(Object.getPrototypeOf(foo));
console.log('--------');

var foo = {name: 'kitten', age: 23};
console.log(foo.name);
console.log(foo['name']);
var nameProp = 'name';
console.log(foo[nameProp]);
console.log('-------');

console.log(foo);
delete foo.age;
console.log(foo);
console.log('-------');


let obj = {
  name: 'angus',
  age: 23,
  sex: '男'
};

obj.name = undefined;
obj.age = null;
delete obj.sex;

for (let prop in obj) {
  if (obj.hasOwnProperty(prop)) {
    console.log(prop, '-', obj[prop]);
  }
}
console.log('-------');

var test = {
  'case': 'I am a case.',
  delete: 'I am a delete'
};
console.log(test.delete);
