package com.example.Idao;

import java.util.List;

// DAO -> Data Access Object

// declaration de methodes CRUD (create-read-update-delete)
public interface IDao<T> {

	List<T> findAll() throws ClassNotFoundException;

	T save(T o) throws ClassNotFoundException;

	T update(T o, int id) throws ClassNotFoundException;
	
	boolean delete(int id) throws ClassNotFoundException;
	
	T findbyId( int id) throws ClassNotFoundException;
	
	T findbyFirstName(String firstname) throws ClassNotFoundException;
	
	List<T> findbySalaire(double salaire) throws ClassNotFoundException;
	
	List<T> insertAndFindAll(T o) throws ClassNotFoundException;

	
}
