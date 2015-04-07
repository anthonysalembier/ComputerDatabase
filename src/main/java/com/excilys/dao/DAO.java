package com.excilys.dao;

import java.io.Serializable;
import java.util.List;

import com.excilys.exception.DAOException;
import com.excilys.util.Page;

/**
 * @param <T> The entity to manage.
 * @param <I> The id type.
 * @inv
 *     getAll() != null && 0 <= getAll().size()
 */
public interface DAO<T, I extends Serializable> {
	
	/**
	 * Retrieve all entities.
	 * 
	 * @return Entities
	 * @throws DAOException
	 */
	List<T> getAll() throws DAOException;
	
	/**
	 * Retrieve all entities at the asked page.
	 * 
	 * @param page the page wanted
	 * @return Entities
	 * @throws DAOException
	 */
	default List<T> getAll(Page page) throws DAOException {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Retrieve entity by its identifier.
	 * 
	 * @pre id != null
	 * @param id Identifier
	 * @return The matching entity
	 * @throws DAOException
	 */
	T getById(I id) throws DAOException;
	
	/**
	 * Retrieve entity by its name.
	 * 
	 * @pre name != null
	 * @param name Name
	 * @return The matching entity
	 * @throws DAOException
	 */
	default List<T> getByName(String name, Page page) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Create a new entity.
	 * 
	 * @pre entity != null
	 * @param entity
	 * @return The identifier of the entity
	 * @throws DAOException
	 */
	default void create(T entity) throws DAOException {
		throw new UnsupportedOperationException(); 
	}

	/**
	 * Update the entity.
	 * 
	 * @pre entity != null
	 * @param entity Entity to update
	 * @throws DAOException
	 */
	default void update(T entity) throws DAOException {
		throw new UnsupportedOperationException(); 
	}

	/**
	 * Delete a entity by its identifier.
	 * 
	 * @pre id != null
	 * @param id Identifier
	 * @throws DAOException
	 */
	default void delete(I id) throws DAOException {
		throw new UnsupportedOperationException(); 
	}
	
	/**
	 * Count the total number of entities
	 * @throws DAOException
	 */
	int count() throws DAOException;
	
	/**
	 * Count the total number of entities
	 * 
	 * @param name Name
	 * @throws DAOException
	 */
	default int countByName(String name) throws DAOException {
		throw new UnsupportedOperationException();
	}
}
