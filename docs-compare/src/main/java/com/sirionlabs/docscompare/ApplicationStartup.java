package com.sirionlabs.docscompare;

import com.sirionlabs.docscompare.service.LicenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup {

    @Autowired
    private LicenceService licenceService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        licenceService.setLicence();
    }
}
