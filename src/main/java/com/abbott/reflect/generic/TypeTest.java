package com.abbott.reflect.generic;


import com.abbott.reflect.bean.Person;

import java.lang.reflect.*;
import java.util.List;

/**
 * type泛型测试
 * <p>
 * 因为好多框架都使用泛型。典型的例如retrofit。
 * <p>
 * <p>
 * List<T ? entends>[]：这里的List就是ParameterizedType，T就是TypeVariable，T ? entends就是WildcardType（注意，WildcardType不是Java类型，而是一个表达式）
 * ，整个List<T ? entends>[]就是GenericArrayType
 */
public class TypeTest<K extends Person & Cloneable, V> {

    K key;
    V value;


    public <T> List<T> test(List<? extends Person> list1, List<? super Person> list2) {
        return null;

    }

    public <K,V> TypeTest(K k,V v){

    }


    /**
     * ((ParameterizedType)type).getActualTypeArguments()
     * <p>
     * 例如：
     * 1. List<ArrayList> a1;//这里返回的是，ArrayList，Class类型
     * 2. List<ArrayList<String>> a2;//这里返回的是ArrayList<String>，ParameterizedType类型
     * 3. List<T> a3;//返回的是T，TypeVariable类型
     * 4. List<? extends Number> a4; //返回的是WildcardType类型
     * 5. List<ArrayList<String>[]> a5;//GenericArrayType
     */

    public static void testWildType() {
        try {
            Method method = TypeTest.class.getMethod("test", List.class, List.class);
            //拿到参数类型
            Type[] types = method.getGenericParameterTypes();
            for (Type type : types) {
                System.out.println("generic parameter type = >" + type);//
                System.out.println("raw type type = >" + ((ParameterizedType) type).getRawType());
                System.out.println("raw owner type = >" + ((ParameterizedType) type).getOwnerType());

                //获得参数化类型中<>里的类型参数的类型，因为可能有多个类型参数，例如Map<K, V>，所以返回的是一个Type[]数组
                Type[] actualTypes = ((ParameterizedType) type).getActualTypeArguments();
                for (Type actual : actualTypes) {
                    WildcardType wildcardType = (WildcardType) actual;
                    System.out.println("通配符表达式类型是：" + wildcardType);

                    if (wildcardType.getLowerBounds().length > 0) {
                        Type[] lowersTypes = wildcardType.getLowerBounds();
                        for (Type lowersType : lowersTypes) {
                            System.out.println("low type = >" + lowersType);
                        }
                    }

                    if (wildcardType.getUpperBounds().length > 0) {
                        Type[] upperBounds = wildcardType.getUpperBounds();
                        for (Type upperBound : upperBounds) {
                            System.out.println("upper type = > " + upperBound);
                        }
                    }
                }
            }

            Type type = method.getGenericReturnType();
            System.out.println("return type = >" + type);

            if(type instanceof ParameterizedType){
                System.out.println("raw type = " + ((ParameterizedType)type).getRawType());
                Type[] actualTypes = ((ParameterizedType)type).getActualTypeArguments();
                for (Type actualType : actualTypes) {
                    if(actualType instanceof TypeVariable){
                        System.out.println("typevariable name = "+((TypeVariable) actualType).getName());
                        System.out.println("typevariable getGenericDeclaration = "+((TypeVariable) actualType).getGenericDeclaration());


                        //这里表示这个对象getGenericDeclaration是method
                        System.out.println("typevariable getGenericDeclaration is method :  "+ (((TypeVariable) actualType).getGenericDeclaration() instanceof Method));
                    }
                }
            }


            System.out.println("----- test  testWildType  over -------");

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    public static void testTypeVariable() {

        Type[] types = TypeTest.class.getTypeParameters();
        for (Type type : types) {
            TypeVariable typeVariable = (TypeVariable) type;
            System.out.println("TypeTest genericDeclaration =>" + typeVariable.getGenericDeclaration());
            System.out.println("TypeTest typeVariable name =>" + typeVariable.getName());

            Type[] bounds = typeVariable.getBounds();
            for (Type bound : bounds) {
                System.out.println("TypeTest bounds =>" + bound);
            }

            System.out.println("---------gap line-------------");
        }


    }


    public static void main(String[] args) {

        testWildType();
        testTypeVariable();

    }
}
