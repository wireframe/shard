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
package com.codecrate.shard.search;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;

public class ObjectIndexer {
    private static final Log LOG = LogFactory.getLog(ObjectIndexer.class);
    private static final boolean DO_NOT_CREATE_INDEX = false;

    private final Directory directory;
    private final Analyzer analyzer;

    public ObjectIndexer(DirectoryManager directoryManager, Analyzer analyzer) {
        this.directory = directoryManager.getDirectory();
        this.analyzer = analyzer;
    }

    public void save(Serializable id, Object entity) {
        removeDocuments(id);

        IndexWriter writer = null;
        try {
            writer = new IndexWriter(directory, analyzer, DO_NOT_CREATE_INDEX);
            Document document = new Document();
            document.add(Field.Keyword(HibernateObjectSearcher.FIELD_CLASS, entity.getClass().getName()));
            document.add(Field.Keyword(HibernateObjectSearcher.FIELD_ID, id.toString()));
            document.add(Field.Text(HibernateObjectSearcher.FIELD_TEXT, entity.toString()));

            LOG.debug("saving " + document);
            writer.addDocument(document);
        } catch (IOException e) {
            LOG.error("Error updating index for object " + entity, e);
        } finally {
            closeWriter(writer);
        }
    }

    public void delete(Serializable id) {
        removeDocuments(id);
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

    private void removeDocuments(Serializable id) {
        IndexReader reader = null;
        try {
            reader = IndexReader.open(directory);
            int numDeleted = reader.delete(new Term("id", id.toString()));
            if (0 < numDeleted) {
                LOG.debug("Removed " + numDeleted + " documents from index " + directory);
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

	public void clean() {
        IndexReader reader = null;
        try {
            reader = IndexReader.open(directory);
            int numberDocuments = reader.numDocs();
			for (int currentDocument = 0; currentDocument < numberDocuments; currentDocument++) {
                reader.delete(currentDocument);
            }
            LOG.debug("Cleaned " + numberDocuments + " documents from index " + directory);
        } catch (IOException e) {
            LOG.error("Error cleaning index " + directory, e);
        } finally {
            closeReader(reader);
        }
	}
}
