/* Copyright 2013 David Wolverton
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.game.listing.repositories.dao.jpa;

import java.io.Serializable;
import java.lang.reflect.Array;

import javax.persistence.EntityManager;


/**
 * <p>
 * Base class for DAOs that uses JPA EnityManagers and JPA Query Language.
 * 
 * <p>
 * The <code>SearchProcessor</code> and <code>EntityManager</code> must be set
 * in order for the DAO to function. Generally, a single
 * SearchProcessor will be associated with an instance of a DAO for
 * the lifetime of the instance, while a new "current" EntityManager will be
 * injected as needed. Make sure that any EntityManager that is used is
 * associated with the same persistence unit (i.e. EntityManagerFactory) as the
 * SearchProcessor.
 * 
 * @author dwolverton
 * 
 */
@SuppressWarnings("unchecked")
public class JPABaseDAO {

	
	private EntityManager entityManager;

	/**
	 * Set the current EntityManager
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Get the current EntityManager
	 */
	protected EntityManager em() {
		return entityManager;
	}

		/**
	 * <p>
	 * Make a transient instance persistent and add it to the datastore. This
	 * operation cascades to associated instances if the association is mapped
	 * with cascade="persist". Throws an error if the entity already exists.
	 * 
	 * <p>
	 * Does not guarantee that the object will be assigned an identifier
	 * immediately. With <code>persist</code> a datastore-generated id may not
	 * be pulled until flush time.
	 */
	protected void _persist(Object... entities) {
		for (Object entity : entities) {
			if (entity != null)
				em().persist(entity);
		}
	}

	
	/**
	 * Return the persistent instance of the given entity class with the given
	 * identifier, or null if there is no such persistent instance.
	 */
	protected <T> T _find(Class<T> type, Serializable id) {
		return em().find(type, id);
	}

		protected <T> T _getReference(Class<T> type, Serializable id) {
		return em().getReference(type, id);
	}

	protected <T> T[] _getReferences(Class<T> type, Serializable... ids) {
		T[] retList = (T[]) Array.newInstance(type, ids.length);
		for (int i = 0; i < ids.length; i++) {
			retList[i] = _getReference(type, ids[i]);
		}
		return retList;
	}


	/**
	 * <p>
	 * Copy the state of the given object onto the persistent object with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instance. If
	 * the given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 * 
	 * <p>
	 * The instance that is passed in does not become associated with the
	 * session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 */
	protected <T> T _merge(T entity) {
		return em().merge(entity);
	}

	/**
	 * <p>
	 * Copy the state of the given objects onto the persistent objects with the
	 * same identifier. If there is no persistent instance currently associated
	 * with the session, it will be loaded. Return the persistent instances. If
	 * a given instance is unsaved, save a copy and return it as a newly
	 * persistent instance.
	 * 
	 * <p>
	 * The instances that are passed in do not become associated with the
	 * session. This operation cascades to associated instances if the
	 * association is mapped with cascade="merge".
	 */
	protected <T> T[] _merge(Class<T> arrayType, T... entities) {
		T[] retList = (T[]) Array.newInstance(arrayType, entities.length);
		for (int i = 0; i < entities.length; i++) {
			retList[i] = _merge(entities[i]);
		}
		return retList;
	}

	



	/**
	 * Returns true if the object is connected to the current hibernate session.
	 */
	protected boolean _contains(Object o) {
		return em().contains(o);
	}

	/**
	 * Flushes changes in the hibernate cache to the datastore.
	 */
	protected void _flush() {
		em().flush();
	}

	/**
	 * Refresh the content of the given entity from the current datastore state.
	 */
	protected void _refresh(Object... entities) {
		for (Object entity : entities) {
			if (entity != null)
				em().refresh(entity);
		}
	}

	
	private boolean validId(Serializable id) {
		if (id == null)
			return false;
		if (id instanceof Number && ((Number) id).equals(0))
			return false;
		if (id instanceof String && "".equals(id))
			return false;
		return true;
	}
}
