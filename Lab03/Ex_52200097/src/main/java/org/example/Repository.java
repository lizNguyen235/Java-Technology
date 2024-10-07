package org.example;

import java.util.List;

public interface Repository <T> {
    boolean add(T t);
    T get(int k);
    List<T> getAll();
    boolean update(T t);
    boolean remove(T k);
    boolean remove(int t);
}
