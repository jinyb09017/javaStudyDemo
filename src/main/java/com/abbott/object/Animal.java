package com.abbott.object;

public class Animal implements Cloneable{

    private String name;
    private String type;

    public Animal(){
        System.out.println("i was called");
    }

    public Animal(String name, String type) {

        System.out.println("i was called with args");

        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
