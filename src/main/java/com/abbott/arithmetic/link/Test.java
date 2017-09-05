package com.abbott.arithmetic.link;

public class Test {
    public static void main(String[] args) {

//        testLinkedList();
        testDoubleLinkedList();

    }

    public static void testLinkedList(){
        //
        LinkedList linkedList = new LinkedList();
        Node node = new Node("jinyb");
        linkedList.add(node);
        Node node1 = new Node("jinyb1");
        linkedList.add(node1);
        Node node2 = new Node("jinyb2");
        linkedList.add(node2);

        linkedList.insert(1,new Node("insert one"));
        linkedList.insert(0,new Node("insert"));
        linkedList.insert(5,new Node("insert last"));

        linkedList.print();
        System.out.println("-----------");

        linkedList.delete(3);

        linkedList.print();
    }


    public  static void testDoubleLinkedList(){
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(new Node("jinyb1"));
        doubleLinkedList.add(new Node("jinyb2"));
        doubleLinkedList.add(new Node("jinyb3"));
        doubleLinkedList.add(new Node("jinyb4"));
        doubleLinkedList.add(new Node("jinyb5"));

        doubleLinkedList.print();

        doubleLinkedList.insert(0,new Node("www"));
        doubleLinkedList.insert(3,new Node("yyy"));
        doubleLinkedList.insert(7,new Node("zzz"));

        doubleLinkedList.print();
    }
}
