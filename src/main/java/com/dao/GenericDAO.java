package com.dao;
import java.util.List;

public interface GenericDAO<T> {

    boolean insert(T obj);

    List<T> getAll();

    T getById(int id);

    boolean update(T obj);

    boolean delete(int id);

}