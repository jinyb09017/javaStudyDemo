package com.abbott.proxy.demo;

import com.abbott.annotation.GET;
import com.abbott.annotation.Map;
import com.abbott.annotation.Query;

/**
 * Created by jinyb on 2017/8/8.
 */
public interface  Api {

    @GET("good")
    public int getData(int a, int pageCount, int pageIndex, @Query("name") @Map("haha") String name, @Query("test") String test);
}
