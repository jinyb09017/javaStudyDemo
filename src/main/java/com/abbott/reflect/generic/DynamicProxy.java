package com.abbott.reflect.generic;

import com.abbott.reflect.bean.Person;
import com.google.gson.Gson;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.lang.reflect.*;

public class DynamicProxy {
    Gson gson = new Gson();

    //返回一个T对象
    public <T> T create(Class<T> tClass) {

        return (T) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{tClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //拿到最里面的泛型信息
                Type returnType = method.getGenericReturnType();
                if (returnType instanceof ParameterizedType) {
                    Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
                    if (types[0] instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) types[0];
                        Type[] types3 = parameterizedType.getActualTypeArguments();
                        Type lastType = types3[0];
                        System.out.println("last type = " + lastType);


                        Response personResponse = new Response<>();
                        personResponse.value = request(method, args, lastType);
                        personResponse.num = 1;

                        return Observable.just(personResponse);
                    }
                }
                return null;
            }
        });

    }

    /**
     * 模块为一个网络请求
     *
     * @param method
     * @param args
     * @param lastType
     * @param <R>
     * @return
     */
    public <R> R request(Method method, Object[] args, Type lastType) {

        //这里构造一个网络请求，返回相应的数据
        if (method.getName().equals("getResponse")) {

            return getResponse(lastType);

        } else if (method.getName().equals("getStoryList")) {

            return getStoryList(args, lastType);

        }

        return null;

    }


    public <R> R getResponse(Type lastType) {
        return gson.fromJson(getPeron(), lastType);
    }

    public <R> R getStoryList(Object[] args, Type lastType) {
        return (R) ("good test" + args[0]);
    }


    public static void main(String[] args) {

        DynamicProxy dynamicProxy = new DynamicProxy();
        ServiceDemo serviceDemo = dynamicProxy.create(ServiceDemo.class);
        serviceDemo.getResponse().subscribe(new Observer<Response<Person>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<Person> personResponse) {

                System.out.println("personResponse.value:" + personResponse.value);
                System.out.println("personResponse.num:" + personResponse.num);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        serviceDemo.getStoryList(2).subscribe(new Observer<Response<String>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<String> stringResponse) {
                System.out.println("personResponse.value:" + stringResponse.value);
                System.out.println("personResponse.num:" + stringResponse.num);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void haha() {


        ServiceDemo personServiceDemo = (ServiceDemo) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{ServiceDemo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("method = " + method);
                System.out.println("args = " + args);
                System.out.println("this = " + this);
//                System.out.println("object = "+proxy);

                Type returnType = method.getGenericReturnType();
                if (returnType instanceof ParameterizedType) {
                    Type[] types = ((ParameterizedType) returnType).getActualTypeArguments();
                    System.out.println("returnType = " + returnType);
                    for (Type type : types) {

                        System.out.println("type = " + type);

                        System.out.println("type  =  person :" + (type == Person.class));


                        System.out.println("type name = " + type.getTypeName());
                        System.out.println("type = " + (type instanceof ParameterizedType));


                        if (type instanceof Class) {
                            System.out.println("good");
                        }


                        //这里实例化一个对象出来
                        String person = getPeron();
                        Person p = gson.fromJson(person, type);
                        System.out.println("good test :" + p.toString());


                    }

                }


                Response<Person> response = new Response<>();
                Person person = new Person("a", "b", "c");
                response.value = person;
                return response;
            }
        });

    }


    public String getPeron() {
        Person person = new Person("good", "jiny", "dd");
        return gson.toJson(person);
    }
}
