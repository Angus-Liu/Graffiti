let user = {
  name: 'angus',
  surname: 'liu',
  _sex: 'man',

  get fullName() {
    return `${this.name} ${this.surname}`;
  },

  set fullName(value) {
    [this.name, this.surname] = value;
  }
};
console.log(user.fullName);
user.fullName = ["Angus", "Liu"];
console.log(user.fullName);

let des = Object.getOwnPropertyDescriptor(user, 'fullName');
console.log(des);


let cat = {
  _name: "little catty",

  get name() {
    return this._name;
  },

  set name(value) {
    if (value.length < 4) {
      // console.log("Name is short!");
      // return;
      throw new Error("Name is short!");
    }
    this._name = value;
  }
};

console.log(cat.name);
cat.name = 'Angus';

function User(name, birthday) {
  this.name = name;
  this.birthday = birthday;

  // this.getAge = function() {
  //   let todayYear = new Date().getFullYear();
  //   return todayYear - this.birthday.getFullYear();
  // }
}

let u = new User('Angus-Liu', new Date(1996, 5, 27));
Object.defineProperty(u, 'age', {
  get() {
    let todayYear = new Date().getFullYear();
    return todayYear - this.birthday.getFullYear();
  }
});
console.log(u.age);
