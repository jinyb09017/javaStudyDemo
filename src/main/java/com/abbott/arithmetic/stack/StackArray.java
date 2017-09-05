package com.abbott.arithmetic.stack;

import java.util.EmptyStackException;

public class StackArray<T> implements Stack<T> {
    //因为泛型在定义的时候，是不能进行new操作。
    private Object[] arr;
    private int top;
    // init size
    private static  int size = 100;


    StackArray(){
        arr = new Object[size];
        top = -1;
    }

    @Override
    public T pop() {

        if(top == -1){
            throw new EmptyStackException();
        }

        //注意了，这里是先返回了arr[top]然后再执行top--的操作。
        return (T) arr[top --];

    }

    @Override
    public void push(T o) {
        top ++;
        arr[top] = o;

    }


    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public T peek() {

        if(top == -1){
            throw new EmptyStackException();
        }
        return (T) arr[top];
    }
}
