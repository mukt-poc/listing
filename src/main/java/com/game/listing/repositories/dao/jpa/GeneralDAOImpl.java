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
import java.util.List;


/**
 * Implementation of <code>GeneralDAO</code> using Hibernate.
 * The SessionFactory property is annotated for automatic resource injection.
 * 
 * @author dwolverton
 */
@SuppressWarnings("unchecked")
public class GeneralDAOImpl extends JPABaseDAO implements GeneralDAO {

	
	public <T> T find(Class<T> type, Serializable id) {
		return (T) _find(type, id);
	}

	
	public void flush() {
		_flush();
	}

	public <T> T getReference(Class<T> type, Serializable id) {
		return _getReference(type, id);
	}

	public <T> T[] getReferences(Class<T> type, Serializable... ids) {
		return _getReferences(type, ids);
	}

	public boolean isAttached(Object entity) {
		return _contains(entity);
	}

	public void refresh(Object... entities) {
		_refresh(entities);
	}

	public <T> T merge(T entity) {
		return _merge(entity);
	}

	public Object[] merge(Object... entities) {
		return _merge(Object.class, entities);
	}

	public void persist(Object... entities) {
		_persist(entities);
	}


	@Override
	public <T> T[] find(Class<T> type, Serializable... ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <T> T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] save(Object... entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean remove(Object entity) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void remove(Object... entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean removeById(Class<?> type, Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void removeByIds(Class<?> type, Serializable... ids) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <T> List<T> findAll(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}


}
