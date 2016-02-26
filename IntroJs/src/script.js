var a;
var b;
var c;

a = scanNumber("a");
b = scanNumber("b");
c = scanNumber("c");

var d = Math.pow(b, 2) - 4 * a * c;

if (d < 0) {
    console.log("Решений нет");
} else if (d == 0) {
    var x = -b / (2 * a);
    console.log("x1 = x2 = " + x);
} else {
    var x1 = (-b + Math.sqrt(d)) / (2 * a);
    var x2 = (-b - Math.sqrt(d)) / (2 * a);
    console.log("x1 = " + x1 + "\nx2 = " + x2)
}

function scanNumber(numberString) {
    while (true) {
        var tmpVar = +prompt("Введите число " + numberString, "");
        if (isNaN(tmpVar)) {
            alert("Введите число");
        } else {
            return tmpVar
        }
    }
}