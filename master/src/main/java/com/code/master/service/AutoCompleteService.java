package com.code.master.service;

import com.code.master.common.Constants;
import com.code.master.index.SearchResultWrapper;
import com.code.master.index.model.ProblemDocument;
import com.code.master.index.model.UserDocument;
import com.code.master.index.repository.ProblemDocumentRepository;
import com.code.master.index.repository.UserDocumentRepository;
import com.code.master.utils.Utils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhrasePrefixQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import com.code.master.index.QueryCompletion;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

public class AutoCompleteService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ProblemDocumentRepository problemDocumentRepository;

    @Autowired
    private UserDocumentRepository userDocumentRepository;

    public List<SearchResultWrapper> search(String inputSearchText, String userId, String orgId) {
        List<SearchResultWrapper> mergedList = new ArrayList<>();
        final String searchText = Utils.GetLastWord(inputSearchText);
        mergedList.addAll(searchUsers(searchText, userId, orgId));
        mergedList.addAll(searchProblems(searchText, userId, orgId));
        return mergedList;
    }

    public List<QueryCompletion> complete(String inputSearchText, String userId, String orgId) {
        List<QueryCompletion> mergedList = new ArrayList<>();
        final String searchText = Utils.GetLastWord(inputSearchText);
        mergedList.addAll(getCompletionsUsers(searchText, userId, orgId));
        mergedList.addAll(getCompletionsProblems(searchText, userId, orgId));
        return mergedList;
    }

    private List<QueryCompletion> getCompletionsUsers(String searchText, String userId, String orgId) {
        List<QueryCompletion> completions = new ArrayList<>();
        if(!elasticsearchRestTemplate.indexOps(IndexCoordinates.of(Constants.USER_DOCUMENT_INDEX)).exists()) {
            System.out.println("The index on jira doesn't exist. Therefore, not searching.");
            return completions;
        }
        final String titleField = "userName";
        MatchPhrasePrefixQueryBuilder prefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery(titleField, searchText);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(prefixQueryBuilder);
        org.springframework.data.elasticsearch.core.query.Query searchQuery =
                new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
        final SearchHits<UserDocument> userDocumentSearchHits =
                elasticsearchRestTemplate.search(searchQuery, UserDocument.class, IndexCoordinates.of(Constants.USER_DOCUMENT_INDEX));
        long numberOfHits = userDocumentSearchHits.getTotalHits();
        if (numberOfHits == 0) {
            System.out.println("No search results found in Jira. Search-Text = " + searchText);
            return completions;
        }
        userDocumentSearchHits.forEach(userDocumentSearchHit -> {
            final UserDocument userDocument = userDocumentSearchHit.getContent();
            QueryCompletion queryCompletion = new QueryCompletion();
            queryCompletion.setDisplayText(Utils.GetWordMatch(userDocument.getUserName(), searchText, Constants.NUM_MAX_WORDS_COMPLETION));
            queryCompletion.setScore(userDocumentSearchHit.getScore());
            queryCompletion.setQueryCompletionType("INDEX");
            completions.add(queryCompletion);
        });
        return completions;
    }

    private List<QueryCompletion> getCompletionsProblems(String searchText, String userId, String orgId) {
        List<QueryCompletion> completions = new ArrayList<>();
        if(!elasticsearchRestTemplate.indexOps(IndexCoordinates.of(Constants.PROBLEM_DOCUMENT_INDEX)).exists()) {
            System.out.println("The index on jira doesn't exist. Therefore, not searching.");
            return completions;
        }
        final String titleField = "userName";
        MatchPhrasePrefixQueryBuilder prefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery(titleField, searchText);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(prefixQueryBuilder);
        org.springframework.data.elasticsearch.core.query.Query searchQuery =
                new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
        final SearchHits<ProblemDocument> problemDocumentSearchHits =
                elasticsearchRestTemplate.search(searchQuery, ProblemDocument.class,
                        IndexCoordinates.of(Constants.PROBLEM_DOCUMENT_INDEX));
        long numberOfHits = problemDocumentSearchHits.getTotalHits();
        if (numberOfHits == 0) {
            System.out.println("No search results found in Jira. Search-Text = " + searchText);
            return completions;
        }
        problemDocumentSearchHits.forEach(problemDocumentSearchHit -> {
            final ProblemDocument problemDocument = problemDocumentSearchHit.getContent();
            QueryCompletion queryCompletion = new QueryCompletion();
            queryCompletion.setDisplayText(Utils.GetWordMatch(problemDocument.getProblemName(),
                    searchText, Constants.NUM_MAX_WORDS_COMPLETION));
            queryCompletion.setScore(problemDocumentSearchHit.getScore());
            queryCompletion.setQueryCompletionType("INDEX");
            completions.add(queryCompletion);
        });
        return completions;
    }

    private List<SearchResultWrapper> searchUsers(String searchText, String userId, String orgId) {
        List<SearchResultWrapper> searchResults = new ArrayList<>();
        if (!elasticsearchRestTemplate.indexOps(IndexCoordinates.of(Constants.USER_DOCUMENT_INDEX)).exists()) {
            System.out.println("The index on jira doesn't exist. Therefore, not searching.");
            return searchResults;
        }
        searchResults.addAll(searchUsersByField(searchText, userId, orgId, "userName"));
        return searchResults;
    }

    private List<SearchResultWrapper> searchUsersByField(String searchText, String userId, String orgId, String fieldName) {
        List<SearchResultWrapper> searchResults = new ArrayList<>();
        System.out.printf("Searching Jira Tickets. SearchText: {%s}, FieldName: {%s}.\n", searchText, fieldName);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(matchQuery(fieldName, searchText));
        org.springframework.data.elasticsearch.core.query.Query searchQuery =
                new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
        final SearchHits<UserDocument> ticketSearchHits = elasticsearchRestTemplate.
                search(searchQuery, UserDocument.class, IndexCoordinates.of(Constants.USER_DOCUMENT_INDEX));
        long numberOfHits = ticketSearchHits.getTotalHits();
        if (numberOfHits == 0) {
            System.out.println("No search results found in Jira. Search-Text = " + searchText);
            return searchResults;
        }
        ticketSearchHits.forEach(userDocumentSearchHit -> {
            final UserDocument userDocument = userDocumentSearchHit.getContent();
            SearchResultWrapper searchResultWrapper = new SearchResultWrapper();
            searchResultWrapper.setId(userDocument.getUserId());
            searchResultWrapper.setTitle(userDocument.getUserName());
            searchResultWrapper.setType("USER");
            searchResults.add(searchResultWrapper);
        });
        return searchResults;
    }

    private List<SearchResultWrapper> searchProblems(String searchText, String userId, String orgId) {
        List<SearchResultWrapper> searchResults = new ArrayList<>();
        if (!elasticsearchRestTemplate.indexOps(IndexCoordinates.of("problemdoc")).exists()) {
            System.out.println("The index on jira doesn't exist. Therefore, not searching.");
            return searchResults;
        }
        searchResults.addAll(searchProblemsByField(searchText, userId, orgId, "problemName"));
        return searchResults;
    }

    private List<SearchResultWrapper> searchProblemsByField(String searchText, String userId, String orgId, String fieldName) {
        List<SearchResultWrapper> searchResults = new ArrayList<>();
        System.out.printf("Searching Jira Tickets. SearchText: {%s}, FieldName: {%s}.\n", searchText, fieldName);
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(matchQuery(fieldName, searchText));
        org.springframework.data.elasticsearch.core.query.Query searchQuery =
                new NativeSearchQueryBuilder().withQuery(boolQueryBuilder).build();
        final SearchHits<ProblemDocument> problemDocumentSearchHits = elasticsearchRestTemplate.
                search(searchQuery, ProblemDocument.class, IndexCoordinates.of(Constants.PROBLEM_DOCUMENT_INDEX));
        long numberOfHits = problemDocumentSearchHits.getTotalHits();
        if (numberOfHits == 0) {
            System.out.println("No search results found in Jira. Search-Text = " + searchText);
            return searchResults;
        }
        problemDocumentSearchHits.forEach(problemDocumentSearchHit -> {
            final ProblemDocument problemDocument = problemDocumentSearchHit.getContent();
            SearchResultWrapper searchResultWrapper = new SearchResultWrapper();
            searchResultWrapper.setId(problemDocument.getProblemId());
            searchResultWrapper.setTitle(problemDocument.getProblemName());
            searchResultWrapper.setType("DOCUMENT");
            searchResults.add(searchResultWrapper);
        });
        return searchResults;
    }
}
