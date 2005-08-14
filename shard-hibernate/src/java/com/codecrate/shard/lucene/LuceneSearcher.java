package com.codecrate.shard.lucene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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

public class LuceneSearcher extends HibernateTemplate {
	public static final String FIELD_CLASS = "class";
	public static final String FIELD_TEXT = "text";
	public static final String FIELD_ID = "id";

	private static final Log LOG = LogFactory.getLog(LuceneSearcher.class);
	private static final boolean REQUIRED = true;
	private static final boolean NOT_PROHIBITED = false;

	private final Directory directory;
	private Analyzer analyzer;

	public LuceneSearcher(DirectoryManager directoryManager) {
		this.directory = directoryManager.getDirectory();
		this.analyzer = new StandardAnalyzer();
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
			LOG.error("Error searching directory " + directory + " for type "
					+ target + " using query " + query, e);
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
