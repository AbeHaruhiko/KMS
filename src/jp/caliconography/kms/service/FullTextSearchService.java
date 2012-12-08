// Copyright 2011 Google Inc. All Rights Reserved.

package jp.caliconography.kms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.OperationResult;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.QueryOptions;
import com.google.appengine.api.search.RemoveException;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.api.search.StatusCode;

/**
 * A demo servlet showing basic text search capabilities. This servlet has a
 * single index shared between all users. It illustrates how to add, search for
 * and remove documents from the shared index.
 * 
 */
public class FullTextSearchService {

	private static final String VOID_REMOVE = "Remove failed due to a null doc ID";

	private static final String VOID_ADD = "Document not added due to empty content";

	/**
	 * The index used by this application. Since we only have one index we
	 * create one instance only. We build an index with the default consistency,
	 * which is Consistency.PER_DOCUMENT. These types of indexes are most
	 * suitable for streams and feeds, and can cope with a high rate of updates.
	 */
	private static final Index INDEX = SearchServiceFactory.getSearchService().getIndex(IndexSpec.newBuilder().setName("shared_index"));

	enum Action {
		ADD, REMOVE, DEFAULT;
	}

	private static final Logger LOG = Logger.getLogger(FullTextSearchService.class.getName());

	private String getOnlyField(Document doc, String fieldName, String defaultValue) {
		if (doc.getFieldCount(fieldName) == 1) {
			return doc.getOnlyField(fieldName).getText();
		}
		LOG.severe("Field " + fieldName + " present " + doc.getFieldCount(fieldName));
		return defaultValue;
	}

	/**
	 * Searches the index for matching documents. If the query is not specified
	 * in the request, we search for any documents.
	 */
	public Results<ScoredDocument> search(String queryStr, int limit) {
		if (queryStr == null) {
			queryStr = "";
		}
		String outcome = null;
		try {
			// Rather than just using a query we build a search request.
			// This allows us to specify other attributes, such as the
			// number of documents to be returned by search.
			Query query = Query.newBuilder().setOptions(QueryOptions.newBuilder().setLimit(limit).
			// for deployed apps, uncomment the line below to demo snippeting.
			// This will not work on the dev_appserver.
			// setFieldsToSnippet("content").
			build()).build(queryStr);
			LOG.info("Sending query " + query);
			return  INDEX.search(query);
		} catch (RuntimeException e) {
			LOG.log(Level.SEVERE, "Search with query '" + queryStr + "' failed", e);
			outcome = "Search failed due to an error: " + e.getMessage();
			return null;
		}
	}

	/**
	 * Removes documents with IDs specified in the given request. In the demo
	 * application we do not perform any authorization checks, thus no user
	 * information is necessary.
	 */
	private String remove(String[] docIds) {
		if (docIds == null) {
			LOG.warning(VOID_REMOVE);
			return VOID_REMOVE;
		}
		List<String> docIdList = Arrays.asList(docIds);
		try {
			INDEX.delete(docIdList);
			return "Documents " + docIdList + " deleted";
		} catch (RemoveException e) {
			List<String> failedIds = findFailedIds(docIdList, e.getResults());
			LOG.log(Level.SEVERE, "Failed to delete documents " + failedIds, e);
			return "Delete failed for " + failedIds;
		}
	}

	/**
	 * A convenience method that correlates document status to the document ID.
	 */
	private List<String> findFailedIds(List<String> docIdList, List<OperationResult> results) {
		List<String> failedIds = new ArrayList<String>();
		Iterator<OperationResult> opIter = results.iterator();
		Iterator<String> idIter = docIdList.iterator();
		while (opIter.hasNext() && idIter.hasNext()) {
			OperationResult result = opIter.next();
			String docId = idIter.next();
			if (!StatusCode.OK.equals(result.getCode())) {
				failedIds.add(docId);
			}
		}
		return failedIds;
	}

}