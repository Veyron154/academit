package ru.courses.morozov;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class PersonMain {
    public static void main(String[] args) {
        Person p1 = new Person("Ivan", 23);
        Person p2 = new Person("Petr", 56);
        Person p3 = new Person("Pavel", 13);
        Person p4 = new Person("Maria", 42);
        Person p5 = new Person("Ivan", 66);
        Person p6 = new Person("Inna", 32);
        Person p7 = new Person("Pavel", 31);
        Person p8 = new Person("Alisa", 18);
        Person p9 = new Person("Maria", 23);
        Person p10 = new Person("Ilya", 61);

        List<Person> personsList = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

        Stream<String> nameString = personsList.stream().map(Person::getName).distinct();

        String distinctNames = nameString.collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(distinctNames);

        OptionalDouble averageAge = personsList.stream().mapToInt(Person::getAge).filter(a -> a <= 18).average();
        averageAge.ifPresent(System.out::println);

        Map<String, Double> personMap = personsList.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));
        System.out.println(personMap.get("Ivan"));

        Stream<Person> decreaseAge = personsList.stream().filter(a -> a.getAge() > 20 && a.getAge() < 45)
                .sorted((a1, a2) -> a2.getAge() - a1.getAge());
        decreaseAge.forEach(System.out::println);
    }
}
