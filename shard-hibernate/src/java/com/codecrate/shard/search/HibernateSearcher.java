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
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.springframework.orm.hibernate.HibernateTemplate;

public class HibernateSearcher extends HibernateTemplate {
    public static final String FIELD_CLASS = "class";
    public static final String FIELD_TEXT = "text";
    public static final String FIELD_ID = "id";

    private static final Log LOG = LogFactory.getLog(HibernateSearcher.class);
    private static final boolean REQUIRED = true;
    private static final boolean NOT_PROHIBITED = false;

    private final Directory directory;
    private final Analyzer analyzer;

    public HibernateSearcher(DirectoryManager directoryManager, Analyzer analyzer) {
        this.directory = directoryManager.getDirectory();
        this.analyzer = analyzer;
    }

    public Collection search(Class target, String query) {
        Searcher searcher = null;
        Collection results = new ArrayList();

        try {
            searcher = new IndexSearcher(directory);
            BooleanQuery masterQuery = new BooleanQuery();
            masterQuery.add(new TermQuery(new Term(FIELD_CLASS, target.getName())), REQUIRED, NOT_PROHIBITED);
            masterQuery.add(QueryParser.parse(makeWildcardQuery(query), FIELD_TEXT, analyzer), REQUIRED, NOT_PROHIBITED);

            LOG.info("Searching for " + masterQuery);

            Hits hits = searcher.search(masterQuery);
            LOG.info("Found " + hits.length() + " matches");
            for (int x = 0; x < hits.length(); x++) {
                Document document = hits.doc(x);
                String id = document.getField(FIELD_ID).stringValue();
                Object result = this.get(target, id);
                if (null == result) {
                    LOG.warn("Search index is out of synch with database.  Unable to find object " + target + " with id " + id);
                } else {
                    results.add(result);
                }
            }

        } catch (Exception e) {
            LOG.error("Error searching directory " + directory + " for type " + target + " using query " + query, e);
        } finally {
            closeSearcher(searcher);
        }

        return results;
    }

    private String makeWildcardQuery(String query) {
        String result = query.trim();
        if (0 != result.length()) {
            if (!result.endsWith("*")) {
                result = result + "*";
            }
        }
        return result;
    }

    private void closeSearcher(Searcher searcher) {
        if (null != searcher) {
            try {
                searcher.close();
            } catch (IOException e) {
                LOG.warn("Error closing searcher", e);
            }
        }
    }
}
