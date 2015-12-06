package ru.courses.morozov;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Scanner;

@SuppressWarnings("unchecked")
public class ReflectionMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Введите имя класса: ");

        Class c = Class.forName(scanner.next());

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Field field = c.getDeclaredField("name");
        field.setAccessible(true);

        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        Method method = c.getMethod("getAge");
        Method method1 = c.getMethod("getName");

        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
        Constructor constructor = c.getConstructor(String.class, int.class);

        Person person = (Person) constructor.newInstance("Pavel", 1989);

        System.out.println(method1.invoke(person));
        System.out.println(method.invoke(person));
        field.set(person, "Petr");
        System.out.println(method1.invoke(person));
    }
}
