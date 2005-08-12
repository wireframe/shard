package com.codecrate.shard.lucene;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.type.Type;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.RAMDirectory;

public class LuceneInterceptor implements Interceptor {
	private static final Log LOG = LogFactory.getLog(LuceneInterceptor.class);

    public boolean onLoad(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
		return false;
	}

    public boolean onFlushDirty(Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types) {
		return false;
	}

	public boolean onSave(Object entity,
			Serializable id, Object[] currentState,
			String[] propertyNames, Type[] types) throws CallbackException {
		RAMDirectory directory = new RAMDirectory();
		Analyzer analyzer = new StandardAnalyzer();
		boolean forceCreate = true;
        try {
        	System.out.println("saving " + entity);
			IndexWriter writer = new IndexWriter(directory, analyzer, forceCreate);
            Document document = new Document();
            writer.addDocument(document);

            writer.close();
		} catch (IOException e) {
			LOG.error("Error updating index for object " + entity, e);
		}

		directory.close();
		return false;
	}

    public void onDelete(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
	}

	public void preFlush(Iterator entities) throws CallbackException {
	}

	public void postFlush(Iterator entities) throws CallbackException {
	}

	public Boolean isUnsaved(Object entity) {
		return null;
	}

	public int[] findDirty(Object entity,
			Serializable id,
			Object[] state,
			Object[] currentState,
			String[] propertyNames,
			Type[] types) {
		return null;
	}

	public Object instantiate(Class entity, Serializable id) throws CallbackException {
		return null;
	}

}
