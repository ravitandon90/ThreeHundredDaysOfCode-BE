package com.code.master.index.repository;

import com.code.master.index.model.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, String> {}


