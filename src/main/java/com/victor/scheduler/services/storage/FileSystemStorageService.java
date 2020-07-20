package com.victor.scheduler.services.storage;

import com.victor.scheduler.config.FileStorageProperties;
import com.victor.scheduler.exceptions.FileStorageException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Primary
@Service
public class FileSystemStorageService implements FileStorageService {
	     
    private FileStorageProperties fileStorageProperties;

    public FileSystemStorageService(FileStorageProperties fileStorageProperties) {
	this.fileStorageProperties = fileStorageProperties;
    }

    @Override
    public String storeFile(File file) {
	return storeFile(file, "");
    }

    @Override
    public String storeFile(File file, String location) {
	InputStream fileInputStream = null;
	try {
	    fileInputStream = new FileInputStream(file);
	} catch (IOException e) {
	    e.printStackTrace();
	}
	Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + "/" + location).toAbsolutePath()
		.normalize();
	try {
	    if (!Files.exists(fileStorageLocation)) {
		Files.createDirectories(fileStorageLocation);
	    }
	} catch (Exception ex) {
	    throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
		    ex);
	}
	// Normalize file name
	String fileName = StringUtils.cleanPath(file.getName());
	try {
	    // Check if the file's name contains invalid characters
	    if (fileName.contains("..")) {
		throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	    }
	    // Copy file to the target location (Replacing existing file with the same name)
	    Files.copy(fileInputStream,fileStorageLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
	    return fileName;
	} catch (IOException ex) {
	    throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
	} finally {
	    try {
		fileInputStream.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @Override
    public boolean deleteFile(String file) {
	// TODO Auto-generated method stub
	return false;
    }



}
