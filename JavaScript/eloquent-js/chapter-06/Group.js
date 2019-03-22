class Group {

  constructor() {
    this.arr = [];
  }

  static from(arr) {
    let group = new Group();
    for (let item of arr) {
      group.add(item);
    }
    return group;
  }

  add(item) {
    if (!this.has(item)) {
      this.arr.push(item);
    }
  }

  delete(item) {
    if (this.has(item)) {
      let index = this.arr.indexOf(item);
      this.arr.splice(index, index + 1);
    }
  }

  has(item) {
    for (let i of this.arr) {
      if (i === item) {
        return true;
      }
    }
    return false;
  }
}

class GroupIterator {
  constructor(group) {
    this.index = 0;
    this.group = group;
  }

  next() {
    if (this.index === this.group.arr.length) {
      return {done: true};
    }
    return {value: this.group.arr[this.index++], done: false};
  }
}

Group.prototype[Symbol.iterator] = function () {
  return new GroupIterator(this);
};


for (let value of Group.from(["a", "b", "c"])) {
  console.log(value);
}
// → a
// → b
// → c
