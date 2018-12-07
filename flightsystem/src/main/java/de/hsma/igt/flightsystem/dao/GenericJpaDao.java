package de.hsma.igt.flightsystem.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class GenericJpaDao<T, ID extends Serializable> implements GernericDao<T, ID> {

	private Class<T> persistentClass;

	private EntityManager entityManager;

	public GenericJpaDao(Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	public T findById(ID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public T update(T entity) {
		// TODO Auto-generated method stub
		return null;
	}
}
