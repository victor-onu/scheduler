package com.victor.scheduler.services;

import com.victor.scheduler.services.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    FileStorageService fileStorageService;

    @Override
    public String storeFile(File file) {
	return fileStorageService.storeFile(file);
    }

    @Override
    public String storeFile(File file, String location) {
	return fileStorageService.storeFile(file, location);

    }


}
