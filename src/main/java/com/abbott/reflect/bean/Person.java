package com.abbott.reflect.bean;

/**
 * Created by jinyb on 2017/7/7.
 */
public class Person {
    private String name;
    public String nickname;
    private static String tag = "Person";
    private final String path;

    public Person(String path, String name, String nickname) {
        this.path = path;
        this.nickname = nickname;
        this.name = name;
    }
}
