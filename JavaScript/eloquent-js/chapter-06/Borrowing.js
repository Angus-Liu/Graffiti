let map = {one: true, two: true, hasOwnProperty: true};
map.hasOwnProperty = Object.prototype.hasOwnProperty;
console.log(map.hasOwnProperty("one"));
