// let {readFile} = require("fs");
// readFile("./prop.ini", "utf8", (error, text) => {
//   if (error) throw error;
//   console.log("The file contains:\n", text);
// });

// const {writeFile, stat} = require("fs");
// writeFile("./prop.ini", "hobby=***", err => {
//   if (err) console.log(err);
//   else console.log("finished");
// });

// const {readFile} = require("fs/promises");
// readFile("./prop.ini", "utf8")
//   .then(text => {
//     console.log(text);
//   });

const {readFileSync} = require("fs");
console.log("The file contains:", readFileSync("./prop.ini", "utf8"));
