package com.abbott.arithmetic.stack;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class StackList<T> implements Stack<T> {
    private List<T> stack;

    StackList(){
        stack = new ArrayList<>();
    }

    @Override
    public T pop() {
        if(stack.size() == 0){
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    @Override
    public void push(T t) {
        stack.add(t);
    }

    @Override
    public boolean isEmpty() {
        return stack.size() == 0;
    }

    @Override
    public T peek() {
        return stack.get(stack.size() - 1);
    }
}
