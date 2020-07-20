package com.victor.scheduler.utils;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.Map;

@Service
public class FreemakerTemplateEngine implements EmailTemplateResolver {
    
    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public String processTemplateIntoString(String templateName, Map<String, Object> models) {
	try {
	    return FreeMarkerTemplateUtils
		    .processTemplateIntoString(freemarkerConfig.getTemplate(templateName),models);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return null;
    }

}
