package com.codecrate.shard.hibernate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

/**
 * 
 * @see http://www.hibernate.org/272.html
 */
public class EnumUserType implements UserType {
	private static final int[] SQL_TYPES = {Types.VARCHAR};

	private Class<Enum> clazz = null;
	
	protected EnumUserType(Class<?> clazz) {
		this.clazz = (Class<Enum>) clazz;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<Enum> returnedClass() {
		return clazz;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
	throws HibernateException, SQLException {
		String name = resultSet.getString(names[0]);
		Object result = null;
		if (!resultSet.wasNull()) {
			result = Enum.valueOf(clazz, name);
		}
		return result;
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) 
	throws HibernateException, SQLException {
		if (null == value) {
			preparedStatement.setNull(index, Types.VARCHAR);
		} else {
			preparedStatement.setString(index, ((Enum)value).name());
		}
	}
	@Override
	public Object deepCopy(Object value) throws HibernateException{
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}
	
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y)
			return true;
		if (null == x || null == y)
			return false;
		return x.equals(y);
	}
}