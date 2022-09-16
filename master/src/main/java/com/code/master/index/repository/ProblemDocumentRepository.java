package com.code.master.index.repository;

import com.code.master.index.model.ProblemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemDocumentRepository extends ElasticsearchRepository<ProblemDocument, String> {}