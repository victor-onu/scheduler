package com.victor.scheduler.services.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


public interface FileStorageService {

    String storeFile(File file);

    String storeFile(File file, String location);

    boolean deleteFile(String file);

    boolean fileExists(String filename);

    void emptyDir(String dirName);

    void createDirectory(String uploadDir);

    String storeFile(MultipartFile file);

    String storeFile(MultipartFile file,String location);


    Resource loadFileAsResource(String fileName);
}
