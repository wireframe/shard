/*
 * Copyright 2004 codecrate consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.codecrate.shard.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import com.codecrate.shard.dice.Dice;
import com.codecrate.shard.dice.DiceExpression;

/**
 * custom hibernate usertype for storing dice expressions.
 * dice expressions are stored as strings.  any expression that 
 * is a valid <code>DiceExpression</code> can be stored.
 * 
 * @see com.codecreate.shard.dice.DiceExpression
 * 
 * @author <a href="mailto:wireframe@dev.java.net">Ryan Sonnek</a>
 */
public class DiceUserType implements UserType {
    private static final int[] TYPES = { Types.VARCHAR };

	
	public int[] sqlTypes() {
		return TYPES;
	}
	
	
    public Class returnedClass() {
        return Dice.class;
    }
    
	
	public boolean equals(Object x, Object y) {
		if (x == y) {
		    return true;
		}
		
		if (x == null || y == null) {
		    return false;
		}
		
		return ((Dice)x).equals(y);
	}

	
	public Object deepCopy(Object x) {
		if (x == null) {
		    return null;
		}
		return new DiceExpression(x.toString());
	}

	
	public boolean isMutable() { 
	    return true; 
	}

	
    public Object nullSafeGet(ResultSet rs, String[] names, Object arg2) throws HibernateException, SQLException {
		String expression = (String) Hibernate.STRING.nullSafeGet(rs, names[0]);
		if (null == expression) {
		    return null;
		}
		return new DiceExpression(expression);
    }
    
	
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        String expression = value.toString();

		Hibernate.STRING.nullSafeSet(st, expression, index);
    }


	
	public Object assemble(Serializable state, Object owner) throws HibernateException {
		return state;
	}

	
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	
	public int hashCode(Object value) throws HibernateException {
		return value.hashCode();
	}

	
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}
}
