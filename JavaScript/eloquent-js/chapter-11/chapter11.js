function* powers(n) {
  for (let current = n;; current += current) {
    yield current;
  }
}
for (let power of powers(3)) {
  if (power > 50) break;
  console.log(power);
}
