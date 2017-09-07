package com.abbott.arithmetic.stack;

import com.abbott.arithmetic.bean.Person;

public class TestStack {
    public static void main(String[] args) {

//        testArrayStack();
        testLinkedStack();
        testLinkedStackTop();
    }

    public static void testArrayStack(){
        StackArray<Person> stackArray = new StackArray<Person>();
        stackArray.push(new Person("jinyb1",2));
        stackArray.push(new Person("jinyb2",2));
        stackArray.push(new Person("jinyb3",2));

        System.out.println(stackArray.pop());
        System.out.println(stackArray.pop());
        System.out.println(stackArray.peek());
        System.out.println(stackArray.pop());

    }

    public static void testLinkedStack(){
        StackLinkedEnd<Person> stackLinked = new StackLinkedEnd<Person>();
        stackLinked.push(new Person("wpd1",1));
        stackLinked.push(new Person("wpd2",1));
        stackLinked.push(new Person("wpd3",1));
        stackLinked.push(new Person("wpd4",1));

        System.out.println(stackLinked.pop());
        System.out.println(stackLinked.pop());
        System.out.println(stackLinked.pop());
        System.out.println(stackLinked.peek());
        System.out.println(stackLinked.pop());
    }


    public static void testLinkedStackTop(){
        StackLinkedTop<Person> stackLinked = new StackLinkedTop<Person>();
        stackLinked.push(new Person("wpd1",1));
        stackLinked.push(new Person("wpd2",1));
        stackLinked.push(new Person("wpd3",1));
        stackLinked.push(new Person("wpd4",1));

        System.out.println(stackLinked.pop());
        System.out.println(stackLinked.pop());
        System.out.println(stackLinked.pop());
        System.out.println(stackLinked.peek());
        System.out.println(stackLinked.pop());
    }
}
