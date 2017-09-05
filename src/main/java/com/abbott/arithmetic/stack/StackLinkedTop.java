package com.abbott.arithmetic.stack;

import java.util.EmptyStackException;

public class StackLinkedTop<T> implements Stack<T> {
    private Node<T> head;
    private int size = 0;


    StackLinkedTop() {

    }

    public static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public boolean end(){
            return this.item == null && this.next == null;
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T result = head.item;

        head = head.next;

        return result;



    }

    @Override
    public void push(T t) {

        head = new Node(t,head);

    }

    @Override
    public boolean isEmpty() {
       return head.end();
    }

    @Override
    public T peek() {
        return head.item;
    }
}
