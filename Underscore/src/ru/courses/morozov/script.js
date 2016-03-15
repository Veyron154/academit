var people = [
    {
        age: 10,
        name: "Ivan",
        lastName: "Ivanov"
    }, {
        age: 15,
        name: "Petr",
        lastName: "Petrov"
    }, {
        age: 29,
        name: "Irina",
        lastName: "Medvedeva"
    }, {
        age: 26,
        name: "Pavel",
        lastName: "Morozov"
    }, {
        age: 76,
        name: "Vladimir",
        lastName: "Stepanov"
    }, {
        age: 52,
        name: "Stepan",
        lastName: "Ivanov"
    }, {
        age: 20,
        name: "Oleg",
        lastName: "Severov"
    }, {
        age: 28,
        name: "Ivan",
        lastName: "Smirnov"
    }, {
        age: 46,
        name: "Nadejda",
        lastName: "Morozova"
    }
];

var sum = _.chain(people)
    .pluck("age")
    .reduce(function (memo, age) {
        return memo + age;
    }, 0)
    .value();

var average = sum / _.size(people);
console.log(average);

var sortedPeople = _.chain(people)
    .filter(function (people) {
        return people.age >= 20 && people.age <= 30;
    })
    .sortBy("age")
    .value();
console.log(sortedPeople);

_.each(people, function (people) {
    people.fullName = people.name + " " + people.lastName;
});
console.log(people);