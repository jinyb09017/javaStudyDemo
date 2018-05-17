package com.abbott.arithmetic.lru;

import com.abbott.arithmetic.bean.Person;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jinyb on 2017/9/7.
 * <p>
 * delegation方式实现更加优雅一些，但是由于没有实现Map接口，所以线程同步就需要自己搞定了
 * <p>
 * 在多线程环境使用时可以使用 Collections.synchronizedMap()方法实现线程安全操作
 */
public class LruDelegation<K, V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    LinkedHashMap<K, V> map;

    public LruDelegation(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        //根据cacheSize和加载因子计算hashmap的capactiy，+1确保当达到cacheSize上限时不会触发hashmap的扩容，
        int capacity = (int) Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTOR) + 1;
        map = new LinkedHashMap(capacity, DEFAULT_LOAD_FACTOR, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    public synchronized int size() {
        return map.size();
    }

    public synchronized void clear() {
        map.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LruDelegation<Person,Integer> lruDelegation = new LruDelegation<Person, Integer>(5);
        Person p = new Person("name",3);
        lruDelegation.put(new Person("name",1),1);
        lruDelegation.put(new Person("name",2),1);
        lruDelegation.put(p,1);
        lruDelegation.put(new Person("name",4),1);
        lruDelegation.put(new Person("name",5),1);
        lruDelegation.put(new Person("name",6),1);


        lruDelegation.get(p);
        System.out.println(lruDelegation.toString());
    }
}
