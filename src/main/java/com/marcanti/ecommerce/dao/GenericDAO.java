package com.marcanti.ecommerce.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	void create(T entity);

	T edit(T entity);

	void remove(T entity);

	T find(Object id);

	List<T> findAll();

	List<T> findRange(int[] range);

	int count();
}
