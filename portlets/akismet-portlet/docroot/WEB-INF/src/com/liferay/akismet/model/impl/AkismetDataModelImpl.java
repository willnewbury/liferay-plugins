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

package com.liferay.akismet.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.akismet.model.AkismetData;
import com.liferay.akismet.model.AkismetDataModel;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the AkismetData service. Represents a row in the &quot;Akismet_AkismetData&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link AkismetDataModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link AkismetDataImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AkismetDataImpl
 * @see AkismetData
 * @see AkismetDataModel
 * @generated
 */
@ProviderType
public class AkismetDataModelImpl extends BaseModelImpl<AkismetData>
	implements AkismetDataModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a akismet data model instance should use the {@link AkismetData} interface instead.
	 */
	public static final String TABLE_NAME = "Akismet_AkismetData";
	public static final Object[][] TABLE_COLUMNS = {
			{ "akismetDataId", Types.BIGINT },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "type_", Types.VARCHAR },
			{ "permalink", Types.VARCHAR },
			{ "referrer", Types.VARCHAR },
			{ "userAgent", Types.VARCHAR },
			{ "userIP", Types.VARCHAR },
			{ "userURL", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("akismetDataId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("type_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("permalink", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("referrer", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userAgent", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userIP", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("userURL", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table Akismet_AkismetData (akismetDataId LONG not null primary key,modifiedDate DATE null,classNameId LONG,classPK LONG,type_ VARCHAR(75) null,permalink VARCHAR(75) null,referrer VARCHAR(75) null,userAgent VARCHAR(75) null,userIP VARCHAR(75) null,userURL VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table Akismet_AkismetData";
	public static final String ORDER_BY_JPQL = " ORDER BY akismetData.akismetDataId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY Akismet_AkismetData.akismetDataId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.akismet.model.AkismetData"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.akismet.model.AkismetData"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.akismet.model.AkismetData"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long CLASSPK_COLUMN_BITMASK = 2L;
	public static final long MODIFIEDDATE_COLUMN_BITMASK = 4L;
	public static final long AKISMETDATAID_COLUMN_BITMASK = 8L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.akismet.model.AkismetData"));

	public AkismetDataModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _akismetDataId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAkismetDataId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _akismetDataId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return AkismetData.class;
	}

	@Override
	public String getModelClassName() {
		return AkismetData.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("akismetDataId", getAkismetDataId());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("permalink", getPermalink());
		attributes.put("referrer", getReferrer());
		attributes.put("userAgent", getUserAgent());
		attributes.put("userIP", getUserIP());
		attributes.put("userURL", getUserURL());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long akismetDataId = (Long)attributes.get("akismetDataId");

		if (akismetDataId != null) {
			setAkismetDataId(akismetDataId);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String permalink = (String)attributes.get("permalink");

		if (permalink != null) {
			setPermalink(permalink);
		}

		String referrer = (String)attributes.get("referrer");

		if (referrer != null) {
			setReferrer(referrer);
		}

		String userAgent = (String)attributes.get("userAgent");

		if (userAgent != null) {
			setUserAgent(userAgent);
		}

		String userIP = (String)attributes.get("userIP");

		if (userIP != null) {
			setUserIP(userIP);
		}

		String userURL = (String)attributes.get("userURL");

		if (userURL != null) {
			setUserURL(userURL);
		}
	}

	@Override
	public long getAkismetDataId() {
		return _akismetDataId;
	}

	@Override
	public void setAkismetDataId(long akismetDataId) {
		_akismetDataId = akismetDataId;
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_columnBitmask |= MODIFIEDDATE_COLUMN_BITMASK;

		if (_originalModifiedDate == null) {
			_originalModifiedDate = _modifiedDate;
		}

		_modifiedDate = modifiedDate;
	}

	public Date getOriginalModifiedDate() {
		return _originalModifiedDate;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@Override
	public String getType() {
		if (_type == null) {
			return StringPool.BLANK;
		}
		else {
			return _type;
		}
	}

	@Override
	public void setType(String type) {
		_type = type;
	}

	@Override
	public String getPermalink() {
		if (_permalink == null) {
			return StringPool.BLANK;
		}
		else {
			return _permalink;
		}
	}

	@Override
	public void setPermalink(String permalink) {
		_permalink = permalink;
	}

	@Override
	public String getReferrer() {
		if (_referrer == null) {
			return StringPool.BLANK;
		}
		else {
			return _referrer;
		}
	}

	@Override
	public void setReferrer(String referrer) {
		_referrer = referrer;
	}

	@Override
	public String getUserAgent() {
		if (_userAgent == null) {
			return StringPool.BLANK;
		}
		else {
			return _userAgent;
		}
	}

	@Override
	public void setUserAgent(String userAgent) {
		_userAgent = userAgent;
	}

	@Override
	public String getUserIP() {
		if (_userIP == null) {
			return StringPool.BLANK;
		}
		else {
			return _userIP;
		}
	}

	@Override
	public void setUserIP(String userIP) {
		_userIP = userIP;
	}

	@Override
	public String getUserURL() {
		if (_userURL == null) {
			return StringPool.BLANK;
		}
		else {
			return _userURL;
		}
	}

	@Override
	public void setUserURL(String userURL) {
		_userURL = userURL;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			AkismetData.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public AkismetData toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (AkismetData)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		AkismetDataImpl akismetDataImpl = new AkismetDataImpl();

		akismetDataImpl.setAkismetDataId(getAkismetDataId());
		akismetDataImpl.setModifiedDate(getModifiedDate());
		akismetDataImpl.setClassNameId(getClassNameId());
		akismetDataImpl.setClassPK(getClassPK());
		akismetDataImpl.setType(getType());
		akismetDataImpl.setPermalink(getPermalink());
		akismetDataImpl.setReferrer(getReferrer());
		akismetDataImpl.setUserAgent(getUserAgent());
		akismetDataImpl.setUserIP(getUserIP());
		akismetDataImpl.setUserURL(getUserURL());

		akismetDataImpl.resetOriginalValues();

		return akismetDataImpl;
	}

	@Override
	public int compareTo(AkismetData akismetData) {
		long primaryKey = akismetData.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AkismetData)) {
			return false;
		}

		AkismetData akismetData = (AkismetData)obj;

		long primaryKey = akismetData.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		AkismetDataModelImpl akismetDataModelImpl = this;

		akismetDataModelImpl._originalModifiedDate = akismetDataModelImpl._modifiedDate;

		akismetDataModelImpl._originalClassNameId = akismetDataModelImpl._classNameId;

		akismetDataModelImpl._setOriginalClassNameId = false;

		akismetDataModelImpl._originalClassPK = akismetDataModelImpl._classPK;

		akismetDataModelImpl._setOriginalClassPK = false;

		akismetDataModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<AkismetData> toCacheModel() {
		AkismetDataCacheModel akismetDataCacheModel = new AkismetDataCacheModel();

		akismetDataCacheModel.akismetDataId = getAkismetDataId();

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			akismetDataCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			akismetDataCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		akismetDataCacheModel.classNameId = getClassNameId();

		akismetDataCacheModel.classPK = getClassPK();

		akismetDataCacheModel.type = getType();

		String type = akismetDataCacheModel.type;

		if ((type != null) && (type.length() == 0)) {
			akismetDataCacheModel.type = null;
		}

		akismetDataCacheModel.permalink = getPermalink();

		String permalink = akismetDataCacheModel.permalink;

		if ((permalink != null) && (permalink.length() == 0)) {
			akismetDataCacheModel.permalink = null;
		}

		akismetDataCacheModel.referrer = getReferrer();

		String referrer = akismetDataCacheModel.referrer;

		if ((referrer != null) && (referrer.length() == 0)) {
			akismetDataCacheModel.referrer = null;
		}

		akismetDataCacheModel.userAgent = getUserAgent();

		String userAgent = akismetDataCacheModel.userAgent;

		if ((userAgent != null) && (userAgent.length() == 0)) {
			akismetDataCacheModel.userAgent = null;
		}

		akismetDataCacheModel.userIP = getUserIP();

		String userIP = akismetDataCacheModel.userIP;

		if ((userIP != null) && (userIP.length() == 0)) {
			akismetDataCacheModel.userIP = null;
		}

		akismetDataCacheModel.userURL = getUserURL();

		String userURL = akismetDataCacheModel.userURL;

		if ((userURL != null) && (userURL.length() == 0)) {
			akismetDataCacheModel.userURL = null;
		}

		return akismetDataCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{akismetDataId=");
		sb.append(getAkismetDataId());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", permalink=");
		sb.append(getPermalink());
		sb.append(", referrer=");
		sb.append(getReferrer());
		sb.append(", userAgent=");
		sb.append(getUserAgent());
		sb.append(", userIP=");
		sb.append(getUserIP());
		sb.append(", userURL=");
		sb.append(getUserURL());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.akismet.model.AkismetData");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>akismetDataId</column-name><column-value><![CDATA[");
		sb.append(getAkismetDataId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>permalink</column-name><column-value><![CDATA[");
		sb.append(getPermalink());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>referrer</column-name><column-value><![CDATA[");
		sb.append(getReferrer());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userAgent</column-name><column-value><![CDATA[");
		sb.append(getUserAgent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userIP</column-name><column-value><![CDATA[");
		sb.append(getUserIP());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userURL</column-name><column-value><![CDATA[");
		sb.append(getUserURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = AkismetData.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			AkismetData.class
		};
	private long _akismetDataId;
	private Date _modifiedDate;
	private Date _originalModifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _type;
	private String _permalink;
	private String _referrer;
	private String _userAgent;
	private String _userIP;
	private String _userURL;
	private long _columnBitmask;
	private AkismetData _escapedModel;
}