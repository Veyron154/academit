var list = [6, 3, 7, 1, 0, 9, 8, 10, 2, 4, 5, 11];

list.sort(function (e1, e2) {
    return e2 - e1;
});

console.log(list);
console.log(list.slice(0, 5));
console.log(list.slice(list.length - 5));

var sum = 0;
for (i = 0; i < list.length; ++i){
    if (list[i] % 2 == 0){
        sum += list[i];
    }
}
console.log(sum);

var list2 = [];
for (var i = 1; i <= 100; ++i) {
    list2.push(i);
}
console.log(list2);