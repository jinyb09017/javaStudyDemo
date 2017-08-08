package com.abbott.reflect.field;

import com.abbott.reflect.bean.Person;

import java.lang.reflect.Field;

/**
 * Class.getDeclaredField(String name);
 * 返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。
 *
 * Class.getDeclaredFields();
 * 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
 *
 * Class.getField(String name);
 * 返回一个 Field 对象，它反映此 Class 对象所表示的类或接口的指定公共成员字段。
 *
 * Class.getField()
 * 返回一个包含某些 Field 对象的数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段。
 */
public class Test {

    public static void main(String[] args) {
        Person person = new Person("dir", "jinyb", "abbott");

        outputPublicField(person);
        outputAllField(person);
    }

    public static void outputPublicField(Person person) {
        System.out.println("----------public fields---------------");
        //这里只输出field为public.也可能通过 Field field = person.getClass().getField(name)来获取
        Field[] fields = person.getClass().getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
            System.out.println(field);
            try {
                System.out.println(field.get(person));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------public fields---------------");
    }


    public static void outputAllField(Person person) {
        System.out.println("----------all fields---------------");
        Field[] fields = person.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
            System.out.println(field.getType());
            System.out.println(field);
            //private必须设置为true才能访问值，否则会招出异常
            if (field.isAccessible() == false) {
                field.setAccessible(true);
            }


            try {
                if (field.getName().equals("name")) {
                    //这里调用方法修改值
                    field.set(person, "my name was changed");
                }

                System.out.println(field.get(person));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        System.out.println("----------all fields---------------");
    }
}
