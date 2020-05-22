package com.sirionlabs.docscompare.model;

import java.util.List;

public class DocumentCompareRequest {

    private Integer requestId;

    private List<DocumentMetadata> documentMetadata;

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public List<DocumentMetadata> getDocumentMetadata() {
        return documentMetadata;
    }

    public void setDocumentMetadata(List<DocumentMetadata> documentMetadata) {
        this.documentMetadata = documentMetadata;
    }
}
