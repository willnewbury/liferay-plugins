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

package com.liferay.akismet.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.akismet.NoSuchDataException;
import com.liferay.akismet.model.AkismetData;
import com.liferay.akismet.model.impl.AkismetDataImpl;
import com.liferay.akismet.model.impl.AkismetDataModelImpl;
import com.liferay.akismet.service.persistence.AkismetDataPersistence;

import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the akismet data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AkismetDataPersistence
 * @see com.liferay.akismet.service.persistence.AkismetDataUtil
 * @generated
 */
@ProviderType
public class AkismetDataPersistenceImpl extends BasePersistenceImpl<AkismetData>
	implements AkismetDataPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link AkismetDataUtil} to access the akismet data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = AkismetDataImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, AkismetDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, AkismetDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE =
		new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, AkismetDataImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByLtModifiedDate",
			new String[] {
				Date.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE =
		new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByLtModifiedDate",
			new String[] { Date.class.getName() });

	/**
	 * Returns all the akismet datas where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the matching akismet datas
	 */
	@Override
	public List<AkismetData> findByLtModifiedDate(Date modifiedDate) {
		return findByLtModifiedDate(modifiedDate, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the akismet datas where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of akismet datas
	 * @param end the upper bound of the range of akismet datas (not inclusive)
	 * @return the range of matching akismet datas
	 */
	@Override
	public List<AkismetData> findByLtModifiedDate(Date modifiedDate, int start,
		int end) {
		return findByLtModifiedDate(modifiedDate, start, end, null);
	}

	/**
	 * Returns an ordered range of all the akismet datas where modifiedDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param modifiedDate the modified date
	 * @param start the lower bound of the range of akismet datas
	 * @param end the upper bound of the range of akismet datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching akismet datas
	 */
	@Override
	public List<AkismetData> findByLtModifiedDate(Date modifiedDate, int start,
		int end, OrderByComparator<AkismetData> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LTMODIFIEDDATE;
		finderArgs = new Object[] { modifiedDate, start, end, orderByComparator };

		List<AkismetData> list = (List<AkismetData>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (AkismetData akismetData : list) {
				if ((modifiedDate.getTime() <= akismetData.getModifiedDate()
															  .getTime())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_AKISMETDATA_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(AkismetDataModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				if (!pagination) {
					list = (List<AkismetData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AkismetData>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Returns the first akismet data in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching akismet data
	 * @throws NoSuchDataException if a matching akismet data could not be found
	 */
	@Override
	public AkismetData findByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<AkismetData> orderByComparator)
		throws NoSuchDataException {
		AkismetData akismetData = fetchByLtModifiedDate_First(modifiedDate,
				orderByComparator);

		if (akismetData != null) {
			return akismetData;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataException(msg.toString());
	}

	/**
	 * Returns the first akismet data in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching akismet data, or <code>null</code> if a matching akismet data could not be found
	 */
	@Override
	public AkismetData fetchByLtModifiedDate_First(Date modifiedDate,
		OrderByComparator<AkismetData> orderByComparator) {
		List<AkismetData> list = findByLtModifiedDate(modifiedDate, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last akismet data in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching akismet data
	 * @throws NoSuchDataException if a matching akismet data could not be found
	 */
	@Override
	public AkismetData findByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<AkismetData> orderByComparator)
		throws NoSuchDataException {
		AkismetData akismetData = fetchByLtModifiedDate_Last(modifiedDate,
				orderByComparator);

		if (akismetData != null) {
			return akismetData;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("modifiedDate=");
		msg.append(modifiedDate);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDataException(msg.toString());
	}

	/**
	 * Returns the last akismet data in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching akismet data, or <code>null</code> if a matching akismet data could not be found
	 */
	@Override
	public AkismetData fetchByLtModifiedDate_Last(Date modifiedDate,
		OrderByComparator<AkismetData> orderByComparator) {
		int count = countByLtModifiedDate(modifiedDate);

		if (count == 0) {
			return null;
		}

		List<AkismetData> list = findByLtModifiedDate(modifiedDate, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the akismet datas before and after the current akismet data in the ordered set where modifiedDate &lt; &#63;.
	 *
	 * @param akismetDataId the primary key of the current akismet data
	 * @param modifiedDate the modified date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next akismet data
	 * @throws NoSuchDataException if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData[] findByLtModifiedDate_PrevAndNext(long akismetDataId,
		Date modifiedDate, OrderByComparator<AkismetData> orderByComparator)
		throws NoSuchDataException {
		AkismetData akismetData = findByPrimaryKey(akismetDataId);

		Session session = null;

		try {
			session = openSession();

			AkismetData[] array = new AkismetDataImpl[3];

			array[0] = getByLtModifiedDate_PrevAndNext(session, akismetData,
					modifiedDate, orderByComparator, true);

			array[1] = akismetData;

			array[2] = getByLtModifiedDate_PrevAndNext(session, akismetData,
					modifiedDate, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected AkismetData getByLtModifiedDate_PrevAndNext(Session session,
		AkismetData akismetData, Date modifiedDate,
		OrderByComparator<AkismetData> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_AKISMETDATA_WHERE);

		boolean bindModifiedDate = false;

		if (modifiedDate == null) {
			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
		}
		else {
			bindModifiedDate = true;

			query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(AkismetDataModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindModifiedDate) {
			qPos.add(new Timestamp(modifiedDate.getTime()));
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(akismetData);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<AkismetData> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the akismet datas where modifiedDate &lt; &#63; from the database.
	 *
	 * @param modifiedDate the modified date
	 */
	@Override
	public void removeByLtModifiedDate(Date modifiedDate) {
		for (AkismetData akismetData : findByLtModifiedDate(modifiedDate,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(akismetData);
		}
	}

	/**
	 * Returns the number of akismet datas where modifiedDate &lt; &#63;.
	 *
	 * @param modifiedDate the modified date
	 * @return the number of matching akismet datas
	 */
	@Override
	public int countByLtModifiedDate(Date modifiedDate) {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_LTMODIFIEDDATE;

		Object[] finderArgs = new Object[] { modifiedDate };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_AKISMETDATA_WHERE);

			boolean bindModifiedDate = false;

			if (modifiedDate == null) {
				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1);
			}
			else {
				bindModifiedDate = true;

				query.append(_FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindModifiedDate) {
					qPos.add(new Timestamp(modifiedDate.getTime()));
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_1 = "akismetData.modifiedDate < NULL";
	private static final String _FINDER_COLUMN_LTMODIFIEDDATE_MODIFIEDDATE_2 = "akismetData.modifiedDate < ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_C_C = new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, AkismetDataImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByC_C",
			new String[] { Long.class.getName(), Long.class.getName() },
			AkismetDataModelImpl.CLASSNAMEID_COLUMN_BITMASK |
			AkismetDataModelImpl.CLASSPK_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_C_C = new FinderPath(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_C",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns the akismet data where classNameId = &#63; and classPK = &#63; or throws a {@link NoSuchDataException} if it could not be found.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching akismet data
	 * @throws NoSuchDataException if a matching akismet data could not be found
	 */
	@Override
	public AkismetData findByC_C(long classNameId, long classPK)
		throws NoSuchDataException {
		AkismetData akismetData = fetchByC_C(classNameId, classPK);

		if (akismetData == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("classNameId=");
			msg.append(classNameId);

			msg.append(", classPK=");
			msg.append(classPK);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDataException(msg.toString());
		}

		return akismetData;
	}

	/**
	 * Returns the akismet data where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the matching akismet data, or <code>null</code> if a matching akismet data could not be found
	 */
	@Override
	public AkismetData fetchByC_C(long classNameId, long classPK) {
		return fetchByC_C(classNameId, classPK, true);
	}

	/**
	 * Returns the akismet data where classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching akismet data, or <code>null</code> if a matching akismet data could not be found
	 */
	@Override
	public AkismetData fetchByC_C(long classNameId, long classPK,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { classNameId, classPK };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs, this);
		}

		if (result instanceof AkismetData) {
			AkismetData akismetData = (AkismetData)result;

			if ((classNameId != akismetData.getClassNameId()) ||
					(classPK != akismetData.getClassPK())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_AKISMETDATA_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				List<AkismetData> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"AkismetDataPersistenceImpl.fetchByC_C(long, long, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					AkismetData akismetData = list.get(0);

					result = akismetData;

					cacheResult(akismetData);

					if ((akismetData.getClassNameId() != classNameId) ||
							(akismetData.getClassPK() != classPK)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
							finderArgs, akismetData);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (AkismetData)result;
		}
	}

	/**
	 * Removes the akismet data where classNameId = &#63; and classPK = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the akismet data that was removed
	 */
	@Override
	public AkismetData removeByC_C(long classNameId, long classPK)
		throws NoSuchDataException {
		AkismetData akismetData = findByC_C(classNameId, classPK);

		return remove(akismetData);
	}

	/**
	 * Returns the number of akismet datas where classNameId = &#63; and classPK = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class p k
	 * @return the number of matching akismet datas
	 */
	@Override
	public int countByC_C(long classNameId, long classPK) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_C_C;

		Object[] finderArgs = new Object[] { classNameId, classPK };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_AKISMETDATA_WHERE);

			query.append(_FINDER_COLUMN_C_C_CLASSNAMEID_2);

			query.append(_FINDER_COLUMN_C_C_CLASSPK_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(classNameId);

				qPos.add(classPK);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_C_C_CLASSNAMEID_2 = "akismetData.classNameId = ? AND ";
	private static final String _FINDER_COLUMN_C_C_CLASSPK_2 = "akismetData.classPK = ?";

	public AkismetDataPersistenceImpl() {
		setModelClass(AkismetData.class);
	}

	/**
	 * Caches the akismet data in the entity cache if it is enabled.
	 *
	 * @param akismetData the akismet data
	 */
	@Override
	public void cacheResult(AkismetData akismetData) {
		EntityCacheUtil.putResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataImpl.class, akismetData.getPrimaryKey(), akismetData);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C,
			new Object[] { akismetData.getClassNameId(), akismetData.getClassPK() },
			akismetData);

		akismetData.resetOriginalValues();
	}

	/**
	 * Caches the akismet datas in the entity cache if it is enabled.
	 *
	 * @param akismetDatas the akismet datas
	 */
	@Override
	public void cacheResult(List<AkismetData> akismetDatas) {
		for (AkismetData akismetData : akismetDatas) {
			if (EntityCacheUtil.getResult(
						AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
						AkismetDataImpl.class, akismetData.getPrimaryKey()) == null) {
				cacheResult(akismetData);
			}
			else {
				akismetData.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all akismet datas.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(AkismetDataImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the akismet data.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(AkismetData akismetData) {
		EntityCacheUtil.removeResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataImpl.class, akismetData.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(akismetData);
	}

	@Override
	public void clearCache(List<AkismetData> akismetDatas) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (AkismetData akismetData : akismetDatas) {
			EntityCacheUtil.removeResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
				AkismetDataImpl.class, akismetData.getPrimaryKey());

			clearUniqueFindersCache(akismetData);
		}
	}

	protected void cacheUniqueFindersCache(AkismetData akismetData) {
		if (akismetData.isNew()) {
			Object[] args = new Object[] {
					akismetData.getClassNameId(), akismetData.getClassPK()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
				akismetData);
		}
		else {
			AkismetDataModelImpl akismetDataModelImpl = (AkismetDataModelImpl)akismetData;

			if ((akismetDataModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						akismetData.getClassNameId(), akismetData.getClassPK()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_C_C, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_C_C, args,
					akismetData);
			}
		}
	}

	protected void clearUniqueFindersCache(AkismetData akismetData) {
		AkismetDataModelImpl akismetDataModelImpl = (AkismetDataModelImpl)akismetData;

		Object[] args = new Object[] {
				akismetData.getClassNameId(), akismetData.getClassPK()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);

		if ((akismetDataModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_C_C.getColumnBitmask()) != 0) {
			args = new Object[] {
					akismetDataModelImpl.getOriginalClassNameId(),
					akismetDataModelImpl.getOriginalClassPK()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_C_C, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_C_C, args);
		}
	}

	/**
	 * Creates a new akismet data with the primary key. Does not add the akismet data to the database.
	 *
	 * @param akismetDataId the primary key for the new akismet data
	 * @return the new akismet data
	 */
	@Override
	public AkismetData create(long akismetDataId) {
		AkismetData akismetData = new AkismetDataImpl();

		akismetData.setNew(true);
		akismetData.setPrimaryKey(akismetDataId);

		return akismetData;
	}

	/**
	 * Removes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param akismetDataId the primary key of the akismet data
	 * @return the akismet data that was removed
	 * @throws NoSuchDataException if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData remove(long akismetDataId) throws NoSuchDataException {
		return remove((Serializable)akismetDataId);
	}

	/**
	 * Removes the akismet data with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the akismet data
	 * @return the akismet data that was removed
	 * @throws NoSuchDataException if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData remove(Serializable primaryKey)
		throws NoSuchDataException {
		Session session = null;

		try {
			session = openSession();

			AkismetData akismetData = (AkismetData)session.get(AkismetDataImpl.class,
					primaryKey);

			if (akismetData == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(akismetData);
		}
		catch (NoSuchDataException nsee) {
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
	protected AkismetData removeImpl(AkismetData akismetData) {
		akismetData = toUnwrappedModel(akismetData);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(akismetData)) {
				akismetData = (AkismetData)session.get(AkismetDataImpl.class,
						akismetData.getPrimaryKeyObj());
			}

			if (akismetData != null) {
				session.delete(akismetData);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (akismetData != null) {
			clearCache(akismetData);
		}

		return akismetData;
	}

	@Override
	public AkismetData updateImpl(AkismetData akismetData) {
		akismetData = toUnwrappedModel(akismetData);

		boolean isNew = akismetData.isNew();

		Session session = null;

		try {
			session = openSession();

			if (akismetData.isNew()) {
				session.save(akismetData);

				akismetData.setNew(false);
			}
			else {
				session.merge(akismetData);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !AkismetDataModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
			AkismetDataImpl.class, akismetData.getPrimaryKey(), akismetData,
			false);

		clearUniqueFindersCache(akismetData);
		cacheUniqueFindersCache(akismetData);

		akismetData.resetOriginalValues();

		return akismetData;
	}

	protected AkismetData toUnwrappedModel(AkismetData akismetData) {
		if (akismetData instanceof AkismetDataImpl) {
			return akismetData;
		}

		AkismetDataImpl akismetDataImpl = new AkismetDataImpl();

		akismetDataImpl.setNew(akismetData.isNew());
		akismetDataImpl.setPrimaryKey(akismetData.getPrimaryKey());

		akismetDataImpl.setAkismetDataId(akismetData.getAkismetDataId());
		akismetDataImpl.setModifiedDate(akismetData.getModifiedDate());
		akismetDataImpl.setClassNameId(akismetData.getClassNameId());
		akismetDataImpl.setClassPK(akismetData.getClassPK());
		akismetDataImpl.setType(akismetData.getType());
		akismetDataImpl.setPermalink(akismetData.getPermalink());
		akismetDataImpl.setReferrer(akismetData.getReferrer());
		akismetDataImpl.setUserAgent(akismetData.getUserAgent());
		akismetDataImpl.setUserIP(akismetData.getUserIP());
		akismetDataImpl.setUserURL(akismetData.getUserURL());

		return akismetDataImpl;
	}

	/**
	 * Returns the akismet data with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the akismet data
	 * @return the akismet data
	 * @throws NoSuchDataException if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDataException {
		AkismetData akismetData = fetchByPrimaryKey(primaryKey);

		if (akismetData == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return akismetData;
	}

	/**
	 * Returns the akismet data with the primary key or throws a {@link NoSuchDataException} if it could not be found.
	 *
	 * @param akismetDataId the primary key of the akismet data
	 * @return the akismet data
	 * @throws NoSuchDataException if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData findByPrimaryKey(long akismetDataId)
		throws NoSuchDataException {
		return findByPrimaryKey((Serializable)akismetDataId);
	}

	/**
	 * Returns the akismet data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the akismet data
	 * @return the akismet data, or <code>null</code> if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData fetchByPrimaryKey(Serializable primaryKey) {
		AkismetData akismetData = (AkismetData)EntityCacheUtil.getResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
				AkismetDataImpl.class, primaryKey);

		if (akismetData == _nullAkismetData) {
			return null;
		}

		if (akismetData == null) {
			Session session = null;

			try {
				session = openSession();

				akismetData = (AkismetData)session.get(AkismetDataImpl.class,
						primaryKey);

				if (akismetData != null) {
					cacheResult(akismetData);
				}
				else {
					EntityCacheUtil.putResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
						AkismetDataImpl.class, primaryKey, _nullAkismetData);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
					AkismetDataImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return akismetData;
	}

	/**
	 * Returns the akismet data with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param akismetDataId the primary key of the akismet data
	 * @return the akismet data, or <code>null</code> if a akismet data with the primary key could not be found
	 */
	@Override
	public AkismetData fetchByPrimaryKey(long akismetDataId) {
		return fetchByPrimaryKey((Serializable)akismetDataId);
	}

	@Override
	public Map<Serializable, AkismetData> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, AkismetData> map = new HashMap<Serializable, AkismetData>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			AkismetData akismetData = fetchByPrimaryKey(primaryKey);

			if (akismetData != null) {
				map.put(primaryKey, akismetData);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			AkismetData akismetData = (AkismetData)EntityCacheUtil.getResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
					AkismetDataImpl.class, primaryKey);

			if (akismetData == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, akismetData);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_AKISMETDATA_WHERE_PKS_IN);

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

			for (AkismetData akismetData : (List<AkismetData>)q.list()) {
				map.put(akismetData.getPrimaryKeyObj(), akismetData);

				cacheResult(akismetData);

				uncachedPrimaryKeys.remove(akismetData.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(AkismetDataModelImpl.ENTITY_CACHE_ENABLED,
					AkismetDataImpl.class, primaryKey, _nullAkismetData);
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
	 * Returns all the akismet datas.
	 *
	 * @return the akismet datas
	 */
	@Override
	public List<AkismetData> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the akismet datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of akismet datas
	 * @param end the upper bound of the range of akismet datas (not inclusive)
	 * @return the range of akismet datas
	 */
	@Override
	public List<AkismetData> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the akismet datas.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AkismetDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of akismet datas
	 * @param end the upper bound of the range of akismet datas (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of akismet datas
	 */
	@Override
	public List<AkismetData> findAll(int start, int end,
		OrderByComparator<AkismetData> orderByComparator) {
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

		List<AkismetData> list = (List<AkismetData>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_AKISMETDATA);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_AKISMETDATA;

				if (pagination) {
					sql = sql.concat(AkismetDataModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<AkismetData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<AkismetData>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the akismet datas from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (AkismetData akismetData : findAll()) {
			remove(akismetData);
		}
	}

	/**
	 * Returns the number of akismet datas.
	 *
	 * @return the number of akismet datas
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_AKISMETDATA);

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

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the akismet data persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(AkismetDataImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_AKISMETDATA = "SELECT akismetData FROM AkismetData akismetData";
	private static final String _SQL_SELECT_AKISMETDATA_WHERE_PKS_IN = "SELECT akismetData FROM AkismetData akismetData WHERE akismetDataId IN (";
	private static final String _SQL_SELECT_AKISMETDATA_WHERE = "SELECT akismetData FROM AkismetData akismetData WHERE ";
	private static final String _SQL_COUNT_AKISMETDATA = "SELECT COUNT(akismetData) FROM AkismetData akismetData";
	private static final String _SQL_COUNT_AKISMETDATA_WHERE = "SELECT COUNT(akismetData) FROM AkismetData akismetData WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "akismetData.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AkismetData exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AkismetData exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(AkismetDataPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"type"
			});
	private static final AkismetData _nullAkismetData = new AkismetDataImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<AkismetData> toCacheModel() {
				return _nullAkismetDataCacheModel;
			}
		};

	private static final CacheModel<AkismetData> _nullAkismetDataCacheModel = new CacheModel<AkismetData>() {
			@Override
			public AkismetData toEntityModel() {
				return _nullAkismetData;
			}
		};
}