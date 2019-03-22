// const square = x => x * x;
//
// console.log(square(100));


// function chicken() {
//   return egg();
// }
//
// function egg() {
//   return chicken();
// }
//
// console.log(chicken() + " came first.");

// function wrapValue(n) {
//   let local = n;
//   return () => local;
// }
//
// let wrap1 = wrapValue(1);
// let wrap2 = wrapValue(2);
// console.log(wrap1());
// console.log(wrap2());


// function printZeroPaddedWithLabel(number, label) {
//   let numberString = String(number);
//   while (numberString.length < 3) {
//     numberString = "0" + numberString;
//   }
//   console.log(`${numberString} ${label}`);
// }
//
// function printFarmInventory(cows, chickens, pigs) {
//   printZeroPaddedWithLabel(cows, "Cows");
//   printZeroPaddedWithLabel(chickens, "Chickens");
//   printZeroPaddedWithLabel(pigs, "Pigs");
// }
//
// printFarmInventory(7, 11, 3);

console.log((4 & 1) === 0);
