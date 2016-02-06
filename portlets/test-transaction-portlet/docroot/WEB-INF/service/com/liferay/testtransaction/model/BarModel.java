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

package com.liferay.testtransaction.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the Bar service. Represents a row in the &quot;TestTransaction_Bar&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.testtransaction.model.impl.BarModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.testtransaction.model.impl.BarImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Bar
 * @see com.liferay.testtransaction.model.impl.BarImpl
 * @see com.liferay.testtransaction.model.impl.BarModelImpl
 * @generated
 */
@ProviderType
public interface BarModel extends BaseModel<Bar> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a bar model instance should use the {@link Bar} interface instead.
	 */

	/**
	 * Returns the primary key of this bar.
	 *
	 * @return the primary key of this bar
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this bar.
	 *
	 * @param primaryKey the primary key of this bar
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the bar ID of this bar.
	 *
	 * @return the bar ID of this bar
	 */
	public long getBarId();

	/**
	 * Sets the bar ID of this bar.
	 *
	 * @param barId the bar ID of this bar
	 */
	public void setBarId(long barId);

	/**
	 * Returns the text of this bar.
	 *
	 * @return the text of this bar
	 */
	@AutoEscape
	public String getText();

	/**
	 * Sets the text of this bar.
	 *
	 * @param text the text of this bar
	 */
	public void setText(String text);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(com.liferay.testtransaction.model.Bar bar);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.testtransaction.model.Bar> toCacheModel();

	@Override
	public com.liferay.testtransaction.model.Bar toEscapedModel();

	@Override
	public com.liferay.testtransaction.model.Bar toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}