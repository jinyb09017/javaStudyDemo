package com.abbott.arithmetic.stack;

public interface Stack<T> {

    T pop();

    void push(T t);

    boolean isEmpty();

    public T peek();

}
