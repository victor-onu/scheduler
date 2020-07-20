package com.victor.scheduler.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomFileValidator implements Validator {
    public static long MAX_SIZE = 10485760;
    public static boolean REQUIRED = false;

    @Override
    public boolean supports(Class<?> clazz) {
	return MultipartFile.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	MultipartFile file = (MultipartFile) target;
	List<String> mimeTypes = new ArrayList<String>() {
	    {
		add("png");
		add("jpg");
		add("jpeg");
	    }
	};
	if (file.isEmpty()) {
	    errors.reject("upload.file.required");

//	} else if (!mimeTypes.contains(FilenameUtils.getExtension(file.getOriginalFilename()))) {
//	    errors.reject("upload.invalid.file.type");
//
	} else if (file.getSize() > MAX_SIZE) {
	    errors.reject("upload.exceeded.file.size");

	}

    }
}