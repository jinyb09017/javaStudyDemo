package com.abbott.arithmetic.stack;

import java.util.EmptyStackException;

public class StackLinkedEnd<T> implements Stack<T> {
    private Node<T> head;
    private int size = 0;


    StackLinkedEnd() {

    }

    public static class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T t;
        if (size == 1) {
            t = head.getItem();
            head = null;
        } else {

            Node<T> last = head;

            //找到last的前一个。
            while (last.getNext().getNext() != null) {
                last = last.getNext();
            }

            Node<T> lastNode = last.getNext();
            t = lastNode.getItem();

            //删除最后的节点
            last.setNext(null);

        }

        size--;
        return t;


    }

    @Override
    public void push(T t) {
        Node node = new Node(t);
        if (head == null) {
            head = node;
        } else {
            Node last = head;
            while (last.getNext() != null) {
                last = last.getNext();
            }

            last.setNext(node);
        }

        size++;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        Node<T> last = head;
        while (last.getNext() != null) {
            last = last.getNext();
        }

        T t = last.getItem();
        //这里需要删除这个节点才行。
        return t;
    }
}
