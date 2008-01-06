package com.codecrate.shard.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

/**
 * 
 * @see http://www.hibernate.org/272.html
 */
public class EnumUserType implements UserType, ParameterizedType {
	private static final int[] SQL_TYPES = {Types.VARCHAR};

	private Class<Enum> clazz = null;

	public void setParameterValues(Properties params) {
		String enumClassName = params.getProperty("enumClassName");
		if (enumClassName == null) {
			throw new IllegalArgumentException("enumClassName parameter not specified");
		}

		try {
			this.clazz = (Class<Enum>) Class.forName(enumClassName);
		} catch (java.lang.ClassNotFoundException e) {
			throw new IllegalArgumentException("enumClass " + enumClassName + " not found", e);
		}
	}

	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	public Class returnedClass() {
		return clazz;
	}

	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
	throws HibernateException, SQLException {
		String name = resultSet.getString(names[0]);
		Object result = null;
		if (!resultSet.wasNull()) {
			result = Enum.valueOf(clazz, name);
		}
		return result;
	}

	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) 
	throws HibernateException, SQLException {
		if (null == value) {
			preparedStatement.setNull(index, Types.VARCHAR);
		} else {
			preparedStatement.setString(index, ((Enum)value).name());
		}
	}

	public Object deepCopy(Object value) throws HibernateException{
		return value;
	}

	public boolean isMutable() {
		return false;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable)value;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}
	
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		if (null == x || null == y)
			return false;
		return x.equals(y);
	}

	@Override
	public Type[] getActualTypeArguments() {
		return null;
	}

	@Override
	public Type getOwnerType() {
		return null;
	}

	@Override
	public Type getRawType() {
		return null;
	}
}