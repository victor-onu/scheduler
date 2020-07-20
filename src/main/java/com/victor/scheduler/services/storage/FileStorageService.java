package com.victor.scheduler.services.storage;

import java.io.File;


public interface FileStorageService {

    String storeFile(File file);

    String storeFile(File file, String location);
    
    boolean deleteFile(String file);


}
