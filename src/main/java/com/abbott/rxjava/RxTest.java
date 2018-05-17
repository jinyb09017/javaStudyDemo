package com.abbott.rxjava;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class RxTest {

    public static void main(String[] args) {
        RxTest rxTest = new RxTest();
//        rxTest.test();
        rxTest.test1();
//        rxTest.test3();
    }

    public void test() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                System.out.println("subscribe is called");
                e.onNext("hello");
                System.out.println("hehe");
                e.onNext("world");
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext data is " + s);

            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError data is " + e.toString());

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");

            }
        });
    }


    public void test1() {
        Observable.just("hello").take(1).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

    }

    public void test2() {
        Observable.just("hello").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {

                return s.length();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });


    }

    public void test3() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("1");
        list.add("23");
        list.add("13");
        Observable.just(list).flatMap(new Function<List<String>, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(List<String> strings) throws Exception {
                return Observable.fromIterable(strings);
            }
        }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                if (s.contains("1")) {
                    return true;
                }
                return false;
            }
        }).take(2).subscribe(new Consumer<String>() {
            @Override
            public void accept(String o) throws Exception {
                System.out.println(o.toString());
            }
        });


    }


}
