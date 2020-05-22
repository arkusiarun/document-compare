package com.sirionlabs.docscompare.service;

import com.sirionlabs.docscompare.model.DocumentCompareRequest;
import com.sirionlabs.docscompare.model.DocumentCompareResponse;

public interface ComparisonService {

    Boolean checkFiles(DocumentCompareRequest request);

    DocumentCompareResponse compare(DocumentCompareRequest compareRequest) throws Exception;
}
