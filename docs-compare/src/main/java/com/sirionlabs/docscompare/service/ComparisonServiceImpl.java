package com.sirionlabs.docscompare.service;

import com.groupdocs.comparison.Comparer;
import com.groupdocs.comparison.common.changes.ChangeInfo;
import com.groupdocs.comparison.common.compareresult.ICompareResult;
import com.groupdocs.comparison.common.comparisonsettings.ComparisonSettings;
import com.sirionlabs.docscompare.constants.CommonConstants;
import com.sirionlabs.docscompare.exception.DocumentCompareException;
import com.sirionlabs.docscompare.model.DocumentCompareRequest;
import com.sirionlabs.docscompare.model.DocumentCompareResponse;
import com.sirionlabs.docscompare.model.DocumentMetadata;
import org.springframework.stereotype.Service;
import com.sirionlabs.docscompare.utils.ListUtils;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
public class ComparisonServiceImpl implements ComparisonService {

    @Override
    public Boolean checkFiles(DocumentCompareRequest request) {
        List<DocumentMetadata> documentMetadataList = request.getDocumentMetadata();
        if (!ListUtils.isEmpty(documentMetadataList)) {
            String extensionBaseFile = documentMetadataList.get(0).getExtension();
            String extensionTranslatedFile = documentMetadataList.get(1).getExtension();
            if (extensionBaseFile.equals(extensionTranslatedFile) && CommonConstants.supportedFileFormat.contains(extensionBaseFile)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public DocumentCompareResponse compare(DocumentCompareRequest request) {
        String downloadUrlBaseFile = null;
        String downloadUrlTranslatedFile = null;
        String extension = null;
        List<DocumentMetadata> documentMetadataList = request.getDocumentMetadata();
        if (!ListUtils.isEmpty(documentMetadataList)) {
            downloadUrlBaseFile = documentMetadataList.get(0).getDownloadUrl();
            extension = documentMetadataList.get(0).getExtension();
            downloadUrlTranslatedFile = documentMetadataList.get(1).getDownloadUrl();
        }
        ICompareResult compareResult = compareFiles(downloadUrlBaseFile, downloadUrlTranslatedFile);
        try {
            return getCompareResultResponse(extension, compareResult);
        } catch (Exception e) {
            throw new DocumentCompareException(e.getMessage());
        }
    }

    private ICompareResult compareFiles(String downloadUrlBaseFile, String downloadUrlTranslatedFile) {
        Comparer comparer = new Comparer();
        ComparisonSettings settings = new ComparisonSettings();
        settings.setShowDeletedContent(true);
        settings.setStyleChangeDetection(true);
        settings.setCalculateComponentCoordinates(true);

        InputStream is1 = null;
        InputStream is2 = null;
        ICompareResult compareResult = comparer.compare(is1, is2, settings);
        if (compareResult == null) {
            throw new DocumentCompareException("Something went wrong. We've got null result.");
        }
        return compareResult;
    }

    private DocumentCompareResponse getCompareResultResponse(String extension, ICompareResult compareResult) throws Exception {
        if (compareResult == null) {
            throw new DocumentCompareException("Something went wrong. We've got null result.");
        }

        DocumentCompareResponse documentCompareResponse = createCompareResultResponse(compareResult, extension);
        String guid = UUID.randomUUID().toString();
        //String savedFile = saveFile(guid, compareResult.getStream(), extension);
        //documentCompareResponse.setGuid(savedFile);
        documentCompareResponse.setExtension(extension);
        //documentCompareResponse.setPages(loadPages(savedFile, null));
        return documentCompareResponse;
    }

    private DocumentCompareResponse createCompareResultResponse(ICompareResult compareResult, String extension) throws Exception {
        DocumentCompareResponse documentCompareResponse = new DocumentCompareResponse();
        ChangeInfo[] changes = getChangeInfos(compareResult, extension);
        documentCompareResponse.setChanges(changes);
        return documentCompareResponse;
    }

    private ChangeInfo[] getChangeInfos(ICompareResult compareResult, String fileExt) {
        ChangeInfo[] changes = compareResult.getChanges();
        for (int i = 0; i < changes.length; i++) {
            ChangeInfo change = changes[i];
            if (CommonConstants.DOC.equals(fileExt) || CommonConstants.DOCX.equals(fileExt)) {
                change.getBox().setY(change.getPageInfo().getHeight() - change.getBox().getY());
            }
            int id = change.getPageInfo().getId();
            change.getPageInfo().setId(id > 0 ? id - 1 : id);
        }
        return changes;
    }
}
