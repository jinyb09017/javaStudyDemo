package com.abbott.object;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * 合建对象的四种方法
 *
 * 1、用new关键字创建
 * 2、利用反射 调用java.lang.Class或者java.lang.reflect.Constructor类的newInstance()
 * 3、调用对象clone方法
 * 4、运用反序列化手段 调用java.io.ObjectInputStream对象的readObject方法。
 *
 * 1和2都会明确的显示的调用构造函数
 * 3是在内存上对已有对象的影印，不会调用构架函数
 * 4从文件中还原类的对象，也不会调用构造函数。
 */

public class Test {

    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }

    @Timer
    //通常情况下，如果类没有构造参数，则会自动创建一个无参的构造函数。
    //如果已经有有参构造函数，则不会自动创建无参构造函数
    public static void newObject(){

        Animal animal = new Animal();

    }

    @Timer
    //class.newInstance只适合无参构造函数
    public static void newInstance(){
        try {
            Class<?> animalClass = Class.forName("com.abbott.object.Animal");
            Animal animal = (Animal) animalClass.newInstance();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Timer
    public static void reflectConstruct(){
        Class<?> animalClass = null;
        try {
            animalClass = Class.forName("com.abbott.object.Animal");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Constructor[] constructors = animalClass.getConstructors();

        System.out.println(constructors.length);

        for(Constructor  constructor:constructors){
            Class[] parameterTypes = constructor.getParameterTypes();
            //拿到所有构造函数的参数类型。所以这里又是一个数组。
            for (Class parameterType : parameterTypes) {
                System.out.println(parameterType.getName());
            }
        }


        //获得指定参数的构造函数
        try {
            Constructor constructor = animalClass.getConstructor(String.class,String.class);
            Animal animal = (Animal) constructor.newInstance("dog","big");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    @Timer
    public static void cloneObject(){
        Animal animal = new Animal();
        try {
            Animal animal2 = (Animal) animal.clone();

            System.out.println(animal == animal2);

            String result = animal.getName() == animal2.getName() ? "浅拷贝":"深拷贝";
            System.out.println(result);
        } catch (CloneNotSupportedException e) {
            //如果Animal没有实现Cloneable接口的话，会抛出此异常。
            e.printStackTrace();
        }

    }


    @Timer
    public static void serial(){

        User user = new User();
        user.setName("hollis");
        user.setGender("male");
        user.setAge(23);
        user.setDate(new Date());
        System.out.println(user);

        ObjectOutputStream oos = null;
        try {
            //这里生成的是二进制文件
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeQuiety(oos);
        }
    }

    @Timer
    public static void unSerial(){
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User user = (User) ois.readObject();
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            closeQuiety(ois);
        }
    }

    public static void closeQuiety(ObjectOutputStream oos){
        try {
            oos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void closeQuiety(ObjectInputStream ois){
        try {
            ois.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
