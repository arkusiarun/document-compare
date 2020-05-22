package com.sirionlabs.docscompare.controller;

import com.sirionlabs.docscompare.exception.DocumentCompareException;
import com.sirionlabs.docscompare.model.DocumentCompareRequest;
import com.sirionlabs.docscompare.model.DocumentCompareResponse;
import com.sirionlabs.docscompare.service.ComparisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/comparison")
public class ComparsionController {

    @Autowired
    private ComparisonService comparisonService;

    @RequestMapping(method = RequestMethod.POST, value = "/compare", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public DocumentCompareResponse compare(@RequestBody DocumentCompareRequest compareRequest) {
        try {
            if (comparisonService.checkFiles(compareRequest)) {
                return comparisonService.compare(compareRequest);
            } else {
                throw new DocumentCompareException("Document types are different");
            }
        } catch (Exception e) {
            throw new DocumentCompareException("");
        }
    }

}
