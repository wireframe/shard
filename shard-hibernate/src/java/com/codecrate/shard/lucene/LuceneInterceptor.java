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
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;

public class LuceneInterceptor implements Interceptor {
	private static final Log LOG = LogFactory.getLog(LuceneInterceptor.class);
	private static final boolean DO_NOT_CREATE_INDEX = false;

	private final Directory directory;
	private final Analyzer analyzer;

	public LuceneInterceptor(DirectoryManager directoryManager) {
    	this.directory = directoryManager.getDirectory();
		this.analyzer = new StandardAnalyzer();
	}

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
		removeDocuments(id);

		IndexWriter writer = null;
        try {
			writer = new IndexWriter(directory, analyzer, DO_NOT_CREATE_INDEX);
            Document document = new Document();
            document.add(Field.Keyword("class", entity.getClass().getName()));
            document.add(Field.Keyword("id", id.toString()));
            document.add(Field.Text("text", entity.toString()));

        	LOG.info("saving " + document);
            writer.addDocument(document);
		} catch (IOException e) {
			LOG.error("Error updating index for object " + entity, e);
		} finally {
			closeWriter(writer);
		}

		return false;
	}

	private void closeWriter(IndexWriter writer) {
		if (null != writer) {
		    try {
				writer.close();
			} catch (IOException e) {
				LOG.warn("Error while closing index writer", e);
			}
		}
	}

    public void onDelete(Object entity,
            Serializable id,
            Object[] state,
            String[] propertyNames,
            Type[] types) {
        removeDocuments(id);
	}

	private void removeDocuments(Serializable id) {
		IndexReader reader = null;
        try {
        	reader = IndexReader.open(directory);
            int numDeleted = reader.delete(new Term("id", id.toString()));
            if (0 < numDeleted) {
                LOG.info("Removed " + numDeleted + " documents from index " + directory);
            }
        } catch (IOException e) {
        	LOG.error("Error removing documents for " + id + " from index " + directory, e);
        } finally {
        	closeReader(reader);
        }
	}

	private void closeReader(IndexReader reader) {
		if (null != reader) {
			try {
				reader.close();
			} catch (IOException e) {
				LOG.warn("Error closing index reader for index " + directory, e);
			}
		}
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
