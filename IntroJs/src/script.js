var a;
var b;
var c;

while (true) {
    var inputA = +prompt("Введите число a", "");
    if (isNaN(inputA)) {
        alert("Введите число");
    } else {
        a = inputA;
        break;
    }
}
while (true) {
    var inputB = +prompt("Введите число b", "");
    if (isNaN(inputB)) {
        alert("Введите число");
    } else {
        b = inputB;
        break;
    }
}

while (true) {
    var inputC = +prompt("Введите число c", "");
    if (isNaN(inputC)) {
        alert("Введите число");
    } else {
        c = inputC;
        break;
    }
}

var d = Math.pow(b, 2) - 4 * a * c;

if (d < 0) {
    console.log("Решений нет");
} else if (d == 0) {
    console.log("x1 = x2 = 0");
} else {
    var x1 = (-b + Math.sqrt(d)) / (2 * a);
    var x2 = (-b - Math.sqrt(d)) / (2 * a);
    console.log("x1 = " + x1 + "\nx2 = " + x2)
}