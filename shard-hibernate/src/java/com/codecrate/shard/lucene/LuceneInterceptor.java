package com.codecrate.shard.lucene;

import java.io.Serializable;
import java.util.Iterator;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

public class LuceneInterceptor implements Interceptor {

	public boolean onLoad(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onFlushDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean onSave(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub
		return false;
	}

	public void onDelete(Object arg0, Serializable arg1, Object[] arg2, String[] arg3, Type[] arg4) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public void preFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public void postFlush(Iterator arg0) throws CallbackException {
		// TODO Auto-generated method stub

	}

	public Boolean isUnsaved(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] findDirty(Object arg0, Serializable arg1, Object[] arg2, Object[] arg3, String[] arg4, Type[] arg5) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(Class arg0, Serializable arg1) throws CallbackException {
		// TODO Auto-generated method stub
		return null;
	}

}
