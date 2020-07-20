package com.victor.scheduler.utils;

import java.util.Map;

public interface EmailTemplateResolver {

    public String processTemplateIntoString(String templateName, Map<String, Object> models);

}
