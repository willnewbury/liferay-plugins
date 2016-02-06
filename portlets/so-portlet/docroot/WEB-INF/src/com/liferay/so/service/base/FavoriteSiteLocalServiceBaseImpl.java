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

package com.liferay.so.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.ClassNamePersistence;
import com.liferay.portal.service.persistence.CompanyPersistence;
import com.liferay.portal.service.persistence.GroupPersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import com.liferay.so.model.FavoriteSite;
import com.liferay.so.service.FavoriteSiteLocalService;
import com.liferay.so.service.persistence.FavoriteSiteFinder;
import com.liferay.so.service.persistence.FavoriteSitePersistence;
import com.liferay.so.service.persistence.MemberRequestPersistence;
import com.liferay.so.service.persistence.ProjectsEntryPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the favorite site local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.so.service.impl.FavoriteSiteLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.so.service.impl.FavoriteSiteLocalServiceImpl
 * @see com.liferay.so.service.FavoriteSiteLocalServiceUtil
 * @generated
 */
@ProviderType
public abstract class FavoriteSiteLocalServiceBaseImpl
	extends BaseLocalServiceImpl implements FavoriteSiteLocalService,
		IdentifiableOSGiService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.liferay.so.service.FavoriteSiteLocalServiceUtil} to access the favorite site local service.
	 */

	/**
	 * Adds the favorite site to the database. Also notifies the appropriate model listeners.
	 *
	 * @param favoriteSite the favorite site
	 * @return the favorite site that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FavoriteSite addFavoriteSite(FavoriteSite favoriteSite) {
		favoriteSite.setNew(true);

		return favoriteSitePersistence.update(favoriteSite);
	}

	/**
	 * Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	 *
	 * @param favoriteSiteId the primary key for the new favorite site
	 * @return the new favorite site
	 */
	@Override
	public FavoriteSite createFavoriteSite(long favoriteSiteId) {
		return favoriteSitePersistence.create(favoriteSiteId);
	}

	/**
	 * Deletes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param favoriteSiteId the primary key of the favorite site
	 * @return the favorite site that was removed
	 * @throws PortalException if a favorite site with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FavoriteSite deleteFavoriteSite(long favoriteSiteId)
		throws PortalException {
		return favoriteSitePersistence.remove(favoriteSiteId);
	}

	/**
	 * Deletes the favorite site from the database. Also notifies the appropriate model listeners.
	 *
	 * @param favoriteSite the favorite site
	 * @return the favorite site that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public FavoriteSite deleteFavoriteSite(FavoriteSite favoriteSite) {
		return favoriteSitePersistence.remove(favoriteSite);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(FavoriteSite.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return favoriteSitePersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) {
		return favoriteSitePersistence.findWithDynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator) {
		return favoriteSitePersistence.findWithDynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return favoriteSitePersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) {
		return favoriteSitePersistence.countWithDynamicQuery(dynamicQuery,
			projection);
	}

	@Override
	public FavoriteSite fetchFavoriteSite(long favoriteSiteId) {
		return favoriteSitePersistence.fetchByPrimaryKey(favoriteSiteId);
	}

	/**
	 * Returns the favorite site with the primary key.
	 *
	 * @param favoriteSiteId the primary key of the favorite site
	 * @return the favorite site
	 * @throws PortalException if a favorite site with the primary key could not be found
	 */
	@Override
	public FavoriteSite getFavoriteSite(long favoriteSiteId)
		throws PortalException {
		return favoriteSitePersistence.findByPrimaryKey(favoriteSiteId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery = new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(com.liferay.so.service.FavoriteSiteLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FavoriteSite.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("favoriteSiteId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		IndexableActionableDynamicQuery indexableActionableDynamicQuery = new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(com.liferay.so.service.FavoriteSiteLocalServiceUtil.getService());
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(FavoriteSite.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"favoriteSiteId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {
		actionableDynamicQuery.setBaseLocalService(com.liferay.so.service.FavoriteSiteLocalServiceUtil.getService());
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(FavoriteSite.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("favoriteSiteId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {
		return favoriteSiteLocalService.deleteFavoriteSite((FavoriteSite)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {
		return favoriteSitePersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the favorite sites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.so.model.impl.FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of favorite sites
	 * @param end the upper bound of the range of favorite sites (not inclusive)
	 * @return the range of favorite sites
	 */
	@Override
	public List<FavoriteSite> getFavoriteSites(int start, int end) {
		return favoriteSitePersistence.findAll(start, end);
	}

	/**
	 * Returns the number of favorite sites.
	 *
	 * @return the number of favorite sites
	 */
	@Override
	public int getFavoriteSitesCount() {
		return favoriteSitePersistence.countAll();
	}

	/**
	 * Updates the favorite site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param favoriteSite the favorite site
	 * @return the favorite site that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FavoriteSite updateFavoriteSite(FavoriteSite favoriteSite) {
		return favoriteSitePersistence.update(favoriteSite);
	}

	/**
	 * Returns the favorite site local service.
	 *
	 * @return the favorite site local service
	 */
	public FavoriteSiteLocalService getFavoriteSiteLocalService() {
		return favoriteSiteLocalService;
	}

	/**
	 * Sets the favorite site local service.
	 *
	 * @param favoriteSiteLocalService the favorite site local service
	 */
	public void setFavoriteSiteLocalService(
		FavoriteSiteLocalService favoriteSiteLocalService) {
		this.favoriteSiteLocalService = favoriteSiteLocalService;
	}

	/**
	 * Returns the favorite site persistence.
	 *
	 * @return the favorite site persistence
	 */
	public FavoriteSitePersistence getFavoriteSitePersistence() {
		return favoriteSitePersistence;
	}

	/**
	 * Sets the favorite site persistence.
	 *
	 * @param favoriteSitePersistence the favorite site persistence
	 */
	public void setFavoriteSitePersistence(
		FavoriteSitePersistence favoriteSitePersistence) {
		this.favoriteSitePersistence = favoriteSitePersistence;
	}

	/**
	 * Returns the favorite site finder.
	 *
	 * @return the favorite site finder
	 */
	public FavoriteSiteFinder getFavoriteSiteFinder() {
		return favoriteSiteFinder;
	}

	/**
	 * Sets the favorite site finder.
	 *
	 * @param favoriteSiteFinder the favorite site finder
	 */
	public void setFavoriteSiteFinder(FavoriteSiteFinder favoriteSiteFinder) {
		this.favoriteSiteFinder = favoriteSiteFinder;
	}

	/**
	 * Returns the member request local service.
	 *
	 * @return the member request local service
	 */
	public com.liferay.so.service.MemberRequestLocalService getMemberRequestLocalService() {
		return memberRequestLocalService;
	}

	/**
	 * Sets the member request local service.
	 *
	 * @param memberRequestLocalService the member request local service
	 */
	public void setMemberRequestLocalService(
		com.liferay.so.service.MemberRequestLocalService memberRequestLocalService) {
		this.memberRequestLocalService = memberRequestLocalService;
	}

	/**
	 * Returns the member request persistence.
	 *
	 * @return the member request persistence
	 */
	public MemberRequestPersistence getMemberRequestPersistence() {
		return memberRequestPersistence;
	}

	/**
	 * Sets the member request persistence.
	 *
	 * @param memberRequestPersistence the member request persistence
	 */
	public void setMemberRequestPersistence(
		MemberRequestPersistence memberRequestPersistence) {
		this.memberRequestPersistence = memberRequestPersistence;
	}

	/**
	 * Returns the projects entry local service.
	 *
	 * @return the projects entry local service
	 */
	public com.liferay.so.service.ProjectsEntryLocalService getProjectsEntryLocalService() {
		return projectsEntryLocalService;
	}

	/**
	 * Sets the projects entry local service.
	 *
	 * @param projectsEntryLocalService the projects entry local service
	 */
	public void setProjectsEntryLocalService(
		com.liferay.so.service.ProjectsEntryLocalService projectsEntryLocalService) {
		this.projectsEntryLocalService = projectsEntryLocalService;
	}

	/**
	 * Returns the projects entry persistence.
	 *
	 * @return the projects entry persistence
	 */
	public ProjectsEntryPersistence getProjectsEntryPersistence() {
		return projectsEntryPersistence;
	}

	/**
	 * Sets the projects entry persistence.
	 *
	 * @param projectsEntryPersistence the projects entry persistence
	 */
	public void setProjectsEntryPersistence(
		ProjectsEntryPersistence projectsEntryPersistence) {
		this.projectsEntryPersistence = projectsEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.service.ClassNameLocalService getClassNameLocalService() {
		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.service.ClassNameLocalService classNameLocalService) {
		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {
		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the company local service.
	 *
	 * @return the company local service
	 */
	public com.liferay.portal.service.CompanyLocalService getCompanyLocalService() {
		return companyLocalService;
	}

	/**
	 * Sets the company local service.
	 *
	 * @param companyLocalService the company local service
	 */
	public void setCompanyLocalService(
		com.liferay.portal.service.CompanyLocalService companyLocalService) {
		this.companyLocalService = companyLocalService;
	}

	/**
	 * Returns the company persistence.
	 *
	 * @return the company persistence
	 */
	public CompanyPersistence getCompanyPersistence() {
		return companyPersistence;
	}

	/**
	 * Sets the company persistence.
	 *
	 * @param companyPersistence the company persistence
	 */
	public void setCompanyPersistence(CompanyPersistence companyPersistence) {
		this.companyPersistence = companyPersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.service.GroupLocalService getGroupLocalService() {
		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.service.GroupLocalService groupLocalService) {
		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.liferay.so.model.FavoriteSite",
			favoriteSiteLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.liferay.so.model.FavoriteSite");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return FavoriteSiteLocalService.class.getName();
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return FavoriteSite.class;
	}

	protected String getModelClassName() {
		return FavoriteSite.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = favoriteSitePersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.liferay.so.service.FavoriteSiteLocalService.class)
	protected FavoriteSiteLocalService favoriteSiteLocalService;
	@BeanReference(type = FavoriteSitePersistence.class)
	protected FavoriteSitePersistence favoriteSitePersistence;
	@BeanReference(type = FavoriteSiteFinder.class)
	protected FavoriteSiteFinder favoriteSiteFinder;
	@BeanReference(type = com.liferay.so.service.MemberRequestLocalService.class)
	protected com.liferay.so.service.MemberRequestLocalService memberRequestLocalService;
	@BeanReference(type = MemberRequestPersistence.class)
	protected MemberRequestPersistence memberRequestPersistence;
	@BeanReference(type = com.liferay.so.service.ProjectsEntryLocalService.class)
	protected com.liferay.so.service.ProjectsEntryLocalService projectsEntryLocalService;
	@BeanReference(type = ProjectsEntryPersistence.class)
	protected ProjectsEntryPersistence projectsEntryPersistence;
	@BeanReference(type = com.liferay.counter.kernel.service.CounterLocalService.class)
	protected com.liferay.counter.kernel.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ClassNameLocalService.class)
	protected com.liferay.portal.service.ClassNameLocalService classNameLocalService;
	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;
	@BeanReference(type = com.liferay.portal.service.CompanyLocalService.class)
	protected com.liferay.portal.service.CompanyLocalService companyLocalService;
	@BeanReference(type = CompanyPersistence.class)
	protected CompanyPersistence companyPersistence;
	@BeanReference(type = com.liferay.portal.service.GroupLocalService.class)
	protected com.liferay.portal.service.GroupLocalService groupLocalService;
	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private ClassLoader _classLoader;
	private FavoriteSiteLocalServiceClpInvoker _clpInvoker = new FavoriteSiteLocalServiceClpInvoker();
}