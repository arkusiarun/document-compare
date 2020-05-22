package com.sirionlabs.docscompare.service;

import com.groupdocs.comparison.common.license.License;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class LicenceService {

    @Value("${license.file}")
    private String licencePath;

    public void setLicence() {
        License license = new License();
        try {
            license.setLicense(this.getInputStreamFromResource(licencePath));
        } catch (Exception ex) {

        }
    }

    public InputStream getInputStreamFromResource(String relativeFilePath) {
        return getClass().getResourceAsStream(relativeFilePath);
    }
}
