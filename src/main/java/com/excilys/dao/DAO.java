package com.excilys.dao;

import java.io.Serializable;
import java.util.List;

import com.excilys.exception.DAOException;

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
	 * Retrieve entity by its identifier.
	 * 
	 * @pre id != null
	 * @param id Identifier
	 * @return The matching entity
	 * @throws DAOException
	 */
	T getById(I id) throws DAOException;

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
}
