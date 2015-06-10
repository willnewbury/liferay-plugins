/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.ams.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.NoSuchTypeException;
import com.liferay.ams.model.Type;
import com.liferay.ams.model.impl.TypeImpl;
import com.liferay.ams.model.impl.TypeModelImpl;
import com.liferay.ams.service.persistence.TypePersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TypePersistence
 * @see com.liferay.ams.service.persistence.TypeUtil
 * @generated
 */
@ProviderType
public class TypePersistenceImpl extends BasePersistenceImpl<Type>
	implements TypePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TypeUtil} to access the type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = TypeImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeModelImpl.FINDER_CACHE_ENABLED, TypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeModelImpl.FINDER_CACHE_ENABLED, TypeImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public TypePersistenceImpl() {
		setModelClass(Type.class);
	}

	/**
	 * Caches the type in the entity cache if it is enabled.
	 *
	 * @param type the type
	 */
	@Override
	public void cacheResult(Type type) {
		EntityCacheUtil.putResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey(), type);

		type.resetOriginalValues();
	}

	/**
	 * Caches the types in the entity cache if it is enabled.
	 *
	 * @param types the types
	 */
	@Override
	public void cacheResult(List<Type> types) {
		for (Type type : types) {
			if (EntityCacheUtil.getResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
						TypeImpl.class, type.getPrimaryKey()) == null) {
				cacheResult(type);
			}
			else {
				type.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all types.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(TypeImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the type.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Type type) {
		EntityCacheUtil.removeResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Type> types) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Type type : types) {
			EntityCacheUtil.removeResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
				TypeImpl.class, type.getPrimaryKey());
		}
	}

	/**
	 * Creates a new type with the primary key. Does not add the type to the database.
	 *
	 * @param typeId the primary key for the new type
	 * @return the new type
	 */
	@Override
	public Type create(long typeId) {
		Type type = new TypeImpl();

		type.setNew(true);
		type.setPrimaryKey(typeId);

		return type;
	}

	/**
	 * Removes the type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param typeId the primary key of the type
	 * @return the type that was removed
	 * @throws NoSuchTypeException if a type with the primary key could not be found
	 */
	@Override
	public Type remove(long typeId) throws NoSuchTypeException {
		return remove((Serializable)typeId);
	}

	/**
	 * Removes the type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the type
	 * @return the type that was removed
	 * @throws NoSuchTypeException if a type with the primary key could not be found
	 */
	@Override
	public Type remove(Serializable primaryKey) throws NoSuchTypeException {
		Session session = null;

		try {
			session = openSession();

			Type type = (Type)session.get(TypeImpl.class, primaryKey);

			if (type == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(type);
		}
		catch (NoSuchTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Type removeImpl(Type type) {
		type = toUnwrappedModel(type);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(type)) {
				type = (Type)session.get(TypeImpl.class, type.getPrimaryKeyObj());
			}

			if (type != null) {
				session.delete(type);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (type != null) {
			clearCache(type);
		}

		return type;
	}

	@Override
	public Type updateImpl(Type type) {
		type = toUnwrappedModel(type);

		boolean isNew = type.isNew();

		Session session = null;

		try {
			session = openSession();

			if (type.isNew()) {
				session.save(type);

				type.setNew(false);
			}
			else {
				session.merge(type);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
			TypeImpl.class, type.getPrimaryKey(), type, false);

		type.resetOriginalValues();

		return type;
	}

	protected Type toUnwrappedModel(Type type) {
		if (type instanceof TypeImpl) {
			return type;
		}

		TypeImpl typeImpl = new TypeImpl();

		typeImpl.setNew(type.isNew());
		typeImpl.setPrimaryKey(type.getPrimaryKey());

		typeImpl.setTypeId(type.getTypeId());
		typeImpl.setGroupId(type.getGroupId());
		typeImpl.setName(type.getName());

		return typeImpl;
	}

	/**
	 * Returns the type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the type
	 * @return the type
	 * @throws NoSuchTypeException if a type with the primary key could not be found
	 */
	@Override
	public Type findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTypeException {
		Type type = fetchByPrimaryKey(primaryKey);

		if (type == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return type;
	}

	/**
	 * Returns the type with the primary key or throws a {@link NoSuchTypeException} if it could not be found.
	 *
	 * @param typeId the primary key of the type
	 * @return the type
	 * @throws NoSuchTypeException if a type with the primary key could not be found
	 */
	@Override
	public Type findByPrimaryKey(long typeId) throws NoSuchTypeException {
		return findByPrimaryKey((Serializable)typeId);
	}

	/**
	 * Returns the type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the type
	 * @return the type, or <code>null</code> if a type with the primary key could not be found
	 */
	@Override
	public Type fetchByPrimaryKey(Serializable primaryKey) {
		Type type = (Type)EntityCacheUtil.getResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
				TypeImpl.class, primaryKey);

		if (type == _nullType) {
			return null;
		}

		if (type == null) {
			Session session = null;

			try {
				session = openSession();

				type = (Type)session.get(TypeImpl.class, primaryKey);

				if (type != null) {
					cacheResult(type);
				}
				else {
					EntityCacheUtil.putResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
						TypeImpl.class, primaryKey, _nullType);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
					TypeImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return type;
	}

	/**
	 * Returns the type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param typeId the primary key of the type
	 * @return the type, or <code>null</code> if a type with the primary key could not be found
	 */
	@Override
	public Type fetchByPrimaryKey(long typeId) {
		return fetchByPrimaryKey((Serializable)typeId);
	}

	@Override
	public Map<Serializable, Type> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Type> map = new HashMap<Serializable, Type>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Type type = fetchByPrimaryKey(primaryKey);

			if (type != null) {
				map.put(primaryKey, type);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Type type = (Type)EntityCacheUtil.getResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
					TypeImpl.class, primaryKey);

			if (type == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, type);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_TYPE_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append(String.valueOf(primaryKey));

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Type type : (List<Type>)q.list()) {
				map.put(type.getPrimaryKeyObj(), type);

				cacheResult(type);

				uncachedPrimaryKeys.remove(type.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(TypeModelImpl.ENTITY_CACHE_ENABLED,
					TypeImpl.class, primaryKey, _nullType);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the types.
	 *
	 * @return the types
	 */
	@Override
	public List<Type> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of types
	 * @param end the upper bound of the range of types (not inclusive)
	 * @return the range of types
	 */
	@Override
	public List<Type> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of types
	 * @param end the upper bound of the range of types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of types
	 */
	@Override
	public List<Type> findAll(int start, int end,
		OrderByComparator<Type> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Type> list = (List<Type>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TYPE;

				if (pagination) {
					sql = sql.concat(TypeModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Type>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Type>)QueryUtil.list(q, getDialect(), start,
							end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the types from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Type type : findAll()) {
			remove(type);
		}
	}

	/**
	 * Returns the number of types.
	 *
	 * @return the number of types
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_TYPE);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the type persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(TypeImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_TYPE = "SELECT type FROM Type type";
	private static final String _SQL_SELECT_TYPE_WHERE_PKS_IN = "SELECT type FROM Type type WHERE typeId IN (";
	private static final String _SQL_COUNT_TYPE = "SELECT COUNT(type) FROM Type type";
	private static final String _ORDER_BY_ENTITY_ALIAS = "type.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Type exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(TypePersistenceImpl.class);
	private static final Type _nullType = new TypeImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Type> toCacheModel() {
				return _nullTypeCacheModel;
			}
		};

	private static final CacheModel<Type> _nullTypeCacheModel = new CacheModel<Type>() {
			@Override
			public Type toEntityModel() {
				return _nullType;
			}
		};
}