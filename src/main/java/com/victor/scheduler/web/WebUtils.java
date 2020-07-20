package com.victor.scheduler.web;

import com.victor.scheduler.exceptions.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class WebUtils {
    
    public static File convertMultiPartToFile(MultipartFile file){
	File convFile = new File(file.getOriginalFilename());
	FileOutputStream fos;
	try {
	    fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	} catch (IOException ex) {
	    throw new FileUploadException("",ex);
	}
	}
    
    public static String generateFileName(MultipartFile multipartFile, int width, int height) {
  	StringBuilder filenamePathBuilder = new StringBuilder();
  	filenamePathBuilder.append(new Date().getTime()).append("[").append(width).append("]").append("[")
  		.append(height).append("].").append(FilenameUtils.getExtension(multipartFile.getOriginalFilename()));
  	return filenamePathBuilder.toString().replace(" ", "_");
      }

}
