package com.abbott.arithmetic.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return age == person.age;

    }

    @Override
    public int hashCode() {
        return age;
    }

    public static void main(String[] args) {
        Map<Person,Integer> persons = new HashMap<>();
        Person person = new Person("jinyb",1);

        persons.put(person,2);
        person.setAge(2);
        System.out.println(persons.get(person));
    }
}
