var list = [6, 3, 7, 1, 0, 9, 8, 10, 2, 4, 5, 11];

list.sort(function (e1, e2) {
    return e2 - e1;
});

console.log(list);
console.log(list.slice(0, 5));
console.log(list.slice(list.length - 5));

var list2 = [];

for (var i = 0; i < 100; ++i) {
    list2[i] = i + 1;
}

console.log(list2);
