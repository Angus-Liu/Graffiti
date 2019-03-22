console.log(/abc/.test("abcde"));
// → true
console.log(/abc/.test("abxde"));
// → false

let stock = "1 lemon, 2 cabbages, and 101 eggs";

// 中文看着也很舒服
function minusOne(match, amount, unit) {
  amount = Number(amount) - 1;
  if (amount === 1) { // only one left, remove the 's'
    unit = unit.slice(0, unit.length - 1);
  } else if (amount === 0) {
    amount = "no";
  }
  return amount + " " + unit;
}

console.log(stock.replace(/(\d+) (\w+)/g, minusOne));
// → no lemon, 1 cabbage, and 100 eggs
