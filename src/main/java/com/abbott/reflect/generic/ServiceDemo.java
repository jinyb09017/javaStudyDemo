package com.abbott.reflect.generic;

import com.abbott.reflect.bean.Person;
import io.reactivex.Observable;

public interface ServiceDemo {


    /**
     * 这里封装成一个observer
     * @return
     */
    Observable<Response<Person>> getResponse();



    Observable<Response<String>> getStoryList(int num);
}
